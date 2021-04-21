package com.mihmih.finances.repository;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class CustomPaymentRepositoryImpl implements CustomPaymentRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Payment> findByDateAndPaymentCategory(LocalDate date, PaymentCategory paymentCategory) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        Root<Payment> paymentRoot = cq.from(Payment.class);

        Predicate datePredicate = cb.equal(paymentRoot.get("date"), date);
        Predicate paymentCategoryPredicate = cb.equal(paymentRoot.get("paymentCategory"), paymentCategory);

        cq.where(datePredicate, paymentCategoryPredicate);
        cq.select(paymentRoot);

        TypedQuery<Payment> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
