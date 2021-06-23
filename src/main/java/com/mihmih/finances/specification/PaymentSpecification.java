package com.mihmih.finances.specification;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentSpecification {

    public static Specification<Payment> hasDate(LocalDate date) {

        return ((root, criteriaQuery, criteriaBuilder) -> {
            return date == null
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.equal(root.get("date"), date);
        });
    }

    public static Specification<Payment> hasPaymentCategory(PaymentCategory paymentCategory) {

        return ((root, criteriaQuery, criteriaBuilder) -> {
            return paymentCategory == null
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.equal(root.get("paymentCategory"), paymentCategory);
        });
    }
}
