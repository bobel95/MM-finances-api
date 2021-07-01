package com.mihmih.finances.service;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.AppUserRole;
import com.mihmih.finances.model.api.AppUserResponse;
import com.mihmih.finances.model.api.ChangePasswordRequest;
import com.mihmih.finances.model.api.PaymentResponse;
import com.mihmih.finances.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUserResponse findById(Long appUserId) {

        AppUser user = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new IllegalArgumentException("no user found with id: " + appUserId));


        AppUserResponse response =  new AppUserResponse(user);
        List<PaymentResponse> paymentResponseList = user.getPaymentList()
                .stream()
                .map(PaymentResponse::new)
                .collect(Collectors.toList());

        response.setPaymentList(paymentResponseList);

        return response;
    }

    @Override
    public AppUserResponse addUser(AppUser appUser) {

        appUser.setAppUserRole(AppUserRole.USER);
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
    public ResponseEntity changePassword(ChangePasswordRequest request, Long appUserId) {
        String password = getOne(appUserId).getPassword();
        String requestPrevPassword = request.getPrevPassword();

        if (!password.equals(requestPrevPassword)) {
            throw new IllegalArgumentException("Incorrect password, " + password + " doesn't match " + requestPrevPassword);
        }

        AppUser user = getOne(appUserId);
        user.setPassword(request.getNewPassword());
        appUserRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public AppUser getOne(Long appUserId) {
        return appUserRepository.getOne(appUserId);
    }

    @Override
    public void deleteUser(Long appUserId) {
        appUserRepository.deleteById(appUserId);
    }
}
