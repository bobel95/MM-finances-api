package com.mihmih.finances;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.AppUserRole;
import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.repository.AppUserRepository;
import com.mihmih.finances.repository.PaymentRepository;
import org.apache.tomcat.jni.Local;
import org.javamoney.moneta.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zalando.jackson.datatype.money.MoneyModule;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class FinancesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancesApplication.class, args);
    }

    @Bean
    public MoneyModule moneyModule() {
        return new MoneyModule();
    }

    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository, AppUserRepository appUserRepository) {

        return args -> {
            appUserRepository.save(
                    new AppUser(
                            "John",
                            "Doe",
                            "john@gmail.com",
                            "dinti",
                            AppUserRole.USER)
            );

            paymentRepository.saveAll(Arrays.asList(
                    Payment.builder()
                            .date(LocalDate.now())
                            .money(Money.of(100, "RON"))
                            .paymentCategory(PaymentCategory.CLOTHING)
                            .appUser(appUserRepository.getOne(1L))
                            .build(),
                    Payment.builder()
                            .date(LocalDate.now())
                            .money(Money.of(10, "RON"))
                            .paymentCategory(PaymentCategory.ALCOHOLIC_DRINKS)
                            .appUser(appUserRepository.getOne(1L))
                            .build(),
                    Payment.builder()
                            .date(LocalDate.of(2019, 10, 10))
                            .money(Money.of(10, "RON"))
                            .paymentCategory(PaymentCategory.ALCOHOLIC_DRINKS)
                            .appUser(appUserRepository.getOne(1L))
                            .build(),
                    Payment.builder()
                            .date(LocalDate.of(2021, 5, 14))
                            .money(Money.of(30, "RON"))
                            .paymentCategory(PaymentCategory.INVESTMENTS)
                            .appUser(appUserRepository.getOne(1L))
                            .build(),
                    Payment.builder()
                            .date(LocalDate.of(2021, 4, 12))
                            .money(Money.of(22.43, "RON"))
                            .paymentCategory(PaymentCategory.FOOD)
                            .appUser(appUserRepository.getOne(1L))
                            .build()
                    )
            );


        };
    }
}
