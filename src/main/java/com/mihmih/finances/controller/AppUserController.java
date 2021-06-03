package com.mihmih.finances.controller;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.api.AppUserResponse;
import com.mihmih.finances.model.api.ChangePasswordRequest;
import com.mihmih.finances.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@CrossOrigin
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/{appUserId}")
    public AppUserResponse getUserById(@PathVariable("appUserId") Long appUserId) {
        return appUserService.findById(appUserId);
    }

    @PostMapping()
    public AppUserResponse addUser(@RequestBody AppUser appUser) {
        return appUserService.addUser(appUser);
    }

    @PutMapping()
    public AppUserResponse updateUserInfo(@RequestBody AppUser appUser) {
        return appUserService.updateUser(appUser);
    }

    @DeleteMapping("/{appUserId}")
    public void deleteUserById(@PathVariable("appUserId") Long appUserId) {
        appUserService.deleteUser(appUserId);
    }

    @PostMapping("/change-password/{appUserId}")
    public ResponseEntity changePassword(
            @RequestBody ChangePasswordRequest request,
            @PathVariable("appUserId") Long appUserId) {
        return appUserService.changePassword(request, appUserId);
    }
}
