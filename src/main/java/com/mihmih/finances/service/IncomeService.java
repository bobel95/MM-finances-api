package com.mihmih.finances.service;

import com.mihmih.finances.model.Income;

import java.util.List;

public interface IncomeService {
    List<Income> findAll();
    void deleteIncomeById(Long incomeId);
    Income updateIncome(Income income);
    Income saveIncome(Income income, Long appUserId);
}
