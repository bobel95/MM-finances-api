package com.mihmih.finances.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @SequenceGenerator(name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence")
    private Long id;

    @ApiModelProperty(example = " 'money' : { 'amount': 10, 'currency': 'RON'}")
    private Money money;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_category_id")
    @JsonIgnoreProperties( value = { "paymentList" })
    private PaymentCategory paymentCategory;

    @ManyToOne
    @JsonIgnoreProperties( value = { "paymentList" })
    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

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
