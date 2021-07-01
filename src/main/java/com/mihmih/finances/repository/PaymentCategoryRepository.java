package com.mihmih.finances.repository;

import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.PaymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentCategoryRepository extends JpaRepository<PaymentCategory, Long> {
    PaymentCategory findByCategory(String category);
    List<PaymentCategory> findAllByAppUser(AppUser appUser);
    PaymentCategory findDistinctByCategoryAndAndAppUser(String category, AppUser appUser);
}
