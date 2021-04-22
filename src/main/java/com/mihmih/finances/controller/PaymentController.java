package com.mihmih.finances.controller;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import static com.mihmih.finances.specification.PaymentSpecification.*;

import com.mihmih.finances.model.api.PaymentResponse;
import com.mihmih.finances.service.PaymentService;
import lombok.AllArgsConstructor;
import static org.springframework.data.jpa.domain.Specification.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentResponse> getPayments(
            @RequestParam(value = "category", required = false) PaymentCategory paymentCategory,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return paymentService.findAll(
                where(hasDate(date)).and(hasPaymentCategory(paymentCategory))
        );
    }

    @GetMapping("/{userId}")
    public List<Payment> getUserPayments(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "category", required = false) PaymentCategory paymentCategory,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        // TODO: decide if this endpoint is needed and if its place is in paymentController or AppUserController
        return null;
    }

    @PostMapping("/{appUserId}")
    public PaymentResponse addPayment(
            @PathVariable("appUserId") Long appUserId,
            @RequestBody Payment payment) {

        return paymentService.savePayment(payment, appUserId);
    }

    @PutMapping
    public PaymentResponse updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }


    @DeleteMapping
    public void deletePayment(@RequestBody Payment payment) {
        paymentService.deletePaymentById(payment.getId());
    }
}
