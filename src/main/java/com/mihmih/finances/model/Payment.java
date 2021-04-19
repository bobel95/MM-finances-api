package com.mihmih.finances.model;

import com.sun.istack.NotNull;
import lombok.*;

import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Payment {

    @Id
    @SequenceGenerator(name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence")
    private Long id;


    private Money money;
    private Date date;
    private PaymentCategory paymentCategory;

    public void setMoney(BigDecimal amount, String currency) {
        this.money = Money.of(amount, currency);
    }

}
