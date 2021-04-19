package com.mihmih.finances;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.repository.PaymentRepository;
import org.javamoney.moneta.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zalando.jackson.datatype.money.MoneyModule;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FinancesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancesApplication.class, args);
    }

    @Bean
    public MoneyModule moneyModule() {
        return new MoneyModule();
    }

    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            paymentRepository.saveAll(Arrays.asList(
                    Payment.builder()
                            .date(new Date())
                            .money(Money.of(100, "RON"))
                            .paymentCategory(PaymentCategory.CLOTHING)
                            .build(),
                    Payment.builder()
                            .date(new Date())
                            .money(Money.of(10, "RON"))
                            .paymentCategory(PaymentCategory.ALCOHOLIC_DRINKS)
                            .build()
                    )
            );
        };
    }
}
