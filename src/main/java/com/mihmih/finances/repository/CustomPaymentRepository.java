package com.mihmih.finances.repository;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;

import java.time.LocalDate;
import java.util.List;

public interface CustomPaymentRepository {
    List<Payment> findByDateAndPaymentCategory(
            LocalDate date,
            PaymentCategory paymentCategory
    );
}
