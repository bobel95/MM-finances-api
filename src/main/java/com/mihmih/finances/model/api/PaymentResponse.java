package com.mihmih.finances.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javamoney.moneta.Money;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    public PaymentResponse(Payment payment) {
        this.id       = payment.getId();
        this.money    = payment.getMoney();
        this.date     = payment.getDate();
        this.paymentCategory = payment.getPaymentCategory();
        this.appUser  = new AppUserResponse(payment.getAppUser());
    }

    private Long id;
    private Money money;
    private LocalDate date;
    private PaymentCategory paymentCategory;
    @JsonIgnoreProperties( value = { "paymentList" })
    private AppUserResponse appUser;
}
