package com.mihmih.finances.service;

import com.mihmih.finances.model.PaymentCategory;

import java.util.List;

public interface PaymentCategoryService {
    PaymentCategory findById(Long paymentCategoryId);
    List<PaymentCategory> findAllByUserId(Long userId);
    PaymentCategory save(PaymentCategory paymentCategory);
    void delete(Long PaymentCategoryId);
}
