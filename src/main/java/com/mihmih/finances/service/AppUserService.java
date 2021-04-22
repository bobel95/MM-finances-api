package com.mihmih.finances.service;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.api.AppUserResponse;

public interface AppUserService {
    AppUserResponse findById(Long appUserId);
    AppUserResponse addUser(AppUser appUser);
    AppUserResponse updateUser(AppUser appUser);
    void deleteUser(Long appUserId);

}
