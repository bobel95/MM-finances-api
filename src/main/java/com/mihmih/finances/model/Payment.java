package com.mihmih.finances.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder

public class Payment {

    public Payment() {

    }

    public Payment(Money money, LocalDate date, PaymentCategory paymentCategory) {
        this.money = money;
        this.date = date;
        this.paymentCategory = paymentCategory;
    }

    public Payment(Long id, Money money, LocalDate date, PaymentCategory paymentCategory) {
        this.id = id;
        this.money = money;
        this.date = date;
        this.paymentCategory = paymentCategory;
    }

    @Id
    @SequenceGenerator(name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence")
    private Long id;


    private Money money;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private PaymentCategory paymentCategory;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void setMoney(BigDecimal amount, String currency) {
        this.money = Money.of(amount, currency);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public void setPaymentCategory(PaymentCategory paymentCategory) {
        this.paymentCategory = paymentCategory;
    }
}
