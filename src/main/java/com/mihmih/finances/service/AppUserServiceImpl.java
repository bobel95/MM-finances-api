package com.mihmih.finances.service;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.api.AppUserResponse;
import com.mihmih.finances.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUserResponse findById(Long appUserId) {

        AppUser user = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new IllegalArgumentException("no user found with id: " + appUserId));

        return new AppUserResponse(user);
    }

    @Override
    public AppUserResponse addUser(AppUser appUser) {

        return new AppUserResponse(
                appUserRepository.save(appUser)
        );
    }

    @Override
    public AppUserResponse updateUser(AppUser appUser) {

        AppUser userToUpdate = appUserRepository.getOne(appUser.getId());

        userToUpdate.setPassword(appUser.getPassword());
        userToUpdate.setEmail(appUser.getEmail());
        userToUpdate.setFirstName(appUser.getFirstName());
        userToUpdate.setLastName(appUser.getLastName());

        return new AppUserResponse(
                appUserRepository.save(userToUpdate)
        );
    }

    @Override
    public void deleteUser(Long appUserId) {
        appUserRepository.deleteById(appUserId);
    }
}
