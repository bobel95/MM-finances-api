package com.mihmih.finances.repository;

import com.mihmih.finances.model.Payment;
import com.mihmih.finances.model.PaymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@Repository
public interface PaymentRepository extends
        JpaRepository<Payment, Long>,
        JpaSpecificationExecutor<Payment>
{
    List<Payment> findAllByDate(LocalDate date);

    @Query("select p from Payment p where p.paymentCategory = :searchedCategory")
    List<Payment> findAllByCategory(PaymentCategory searchedCategory);

    List<Payment> findAllByPaymentCategoryAndDate(PaymentCategory paymentCategory, LocalDate date);

    List<Payment> findAllByPaymentCategory(PaymentCategory paymentCategory);


}
