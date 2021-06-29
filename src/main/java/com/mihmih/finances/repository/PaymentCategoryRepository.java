package com.mihmih.finances.repository;

import com.mihmih.finances.model.PaymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCategoryRepository extends JpaRepository<PaymentCategory, Long> {
    PaymentCategory findByCategory(String category);
}
