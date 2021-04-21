package com.mihmih.finances.controller;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.repository.PaymentRepository;
import static com.mihmih.finances.specification.PaymentSpecification.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.Specification.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<Payment> getPayments(
            @RequestParam(value = "category", required = false) PaymentCategory paymentCategory,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return paymentRepository.findAll(
                where(hasDate(date)).and(hasPaymentCategory(paymentCategory))
        );
    }

    @GetMapping("/{userId}")
    public List<Payment> getUserPayments(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "category", required = false) PaymentCategory paymentCategory,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return null;
    }

    @PostMapping
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }
}
