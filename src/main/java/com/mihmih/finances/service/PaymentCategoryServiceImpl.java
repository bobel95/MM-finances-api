package com.mihmih.finances.service;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.model.api.AppUserResponse;
import com.mihmih.finances.repository.AppUserRepository;
import com.mihmih.finances.repository.PaymentCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentCategoryServiceImpl implements PaymentCategoryService {

    private final PaymentCategoryRepository paymentCategoryRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public PaymentCategory findById(Long paymentCategoryId) {
        return paymentCategoryRepository
                .findById(paymentCategoryId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No payment category found with id: " + paymentCategoryId
                ));
    }

    @Override
    public List<PaymentCategory> findAllByUserId(Long userId) {

        AppUser appUser = appUserRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No user found with id: " + userId
                ));

        return paymentCategoryRepository.findAllByAppUser(appUser);
    }

    @Override
    public PaymentCategory save(PaymentCategory paymentCategory) {
        return paymentCategoryRepository.save(paymentCategory);
    }

    @Override
    public void delete(Long paymentCategoryId) {
        paymentCategoryRepository.deleteById(paymentCategoryId);
    }
}
