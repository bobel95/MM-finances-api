package com.mihmih.finances.service;

import com.mihmih.finances.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, s)));
    }
}
