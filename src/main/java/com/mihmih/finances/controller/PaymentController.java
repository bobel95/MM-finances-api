package com.mihmih.finances.controller;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;

import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @PostMapping
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @GetMapping("/{date}")
    public List<Payment> getPaymentsByDate(@PathVariable("date") String date) throws ParseException {

        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);

        return paymentRepository.findAllByDate(LocalDate.of(year, month, day));
    }
}
