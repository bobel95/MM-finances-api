package com.mihmih.finances.service;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.api.PaymentResponse;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> findAll(Specification<Payment> specification);
    void deletePaymentById(Long paymentId);
    PaymentResponse updatePayment(Payment payment);
    PaymentResponse savePayment(Payment payment, Long appUserId);
}
