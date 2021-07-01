package com.mihmih.finances.service;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.api.AppUserResponse;
import com.mihmih.finances.model.api.ChangePasswordRequest;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    AppUserResponse findById(Long appUserId);
    AppUserResponse addUser(AppUser appUser);
    AppUserResponse updateUser(AppUser appUser);
    ResponseEntity changePassword(ChangePasswordRequest request, Long appUserId);
    AppUser getOne(Long appUserId);
    void deleteUser(Long appUserId);
}
