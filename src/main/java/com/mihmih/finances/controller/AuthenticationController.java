package com.mihmih.finances.controller;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.api.AuthenticationRequest;
import com.mihmih.finances.model.api.AuthenticationResponse;
import com.mihmih.finances.repository.AppUserRepository;
import com.mihmih.finances.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            final String jwt = jwtUtil.createToken(
                    authenticationRequest.getUsername(),
                    roles
            );

            AppUser appUser = appUserRepository
                    .findByEmail(
                            authenticationRequest.getUsername()
                    ).orElseThrow(
                            () -> new UsernameNotFoundException("User not found")
                    );

            return ResponseEntity.ok(
                    new AuthenticationResponse(
                            appUser.getId(),
                            appUser.getUsername(),
                            jwt
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username/password", e);
        }
    }
}
