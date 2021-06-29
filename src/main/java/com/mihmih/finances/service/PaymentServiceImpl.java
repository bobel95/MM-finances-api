package com.mihmih.finances.service;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.api.PaymentResponse;
import com.mihmih.finances.repository.PaymentCategoryRepository;
import com.mihmih.finances.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentCategoryRepository paymentCategoryRepository;
    private final AppUserService appUserService;

    @Override
    public List<PaymentResponse> findAll(Specification<Payment> specification) {
        return paymentRepository.findAll(specification).stream()
                .map(PaymentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePaymentById(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public PaymentResponse updatePayment(Payment payment) {
        Payment paymentToUpdate = paymentRepository.getOne(payment.getId());

        paymentToUpdate.setDate(payment.getDate());
        paymentToUpdate.setPaymentCategory(payment.getPaymentCategory());
        paymentToUpdate.setMoney(payment.getMoney());

        return new PaymentResponse(
                paymentRepository.save(paymentToUpdate)
        );
    }

    @Override
    public PaymentResponse savePayment(Payment payment, Long appUserId) {
        payment.setAppUser(appUserService.getOne(appUserId));

        payment.setPaymentCategory(paymentCategoryRepository.findByCategory(
                payment.getPaymentCategory().getCategory()
        ));

        return new PaymentResponse(
                paymentRepository.save(payment)
        );
    }
}
