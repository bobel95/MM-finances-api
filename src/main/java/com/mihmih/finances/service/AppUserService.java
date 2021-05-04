package com.mihmih.finances.service;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.api.AppUserResponse;

public interface AppUserService {
    AppUserResponse findById(Long appUserId);
    AppUserResponse addUser(AppUser appUser);
    AppUserResponse updateUser(AppUser appUser);
    AppUser getOne(Long appUserId);
    void deleteUser(Long appUserId);
}
