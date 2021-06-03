package com.mihmih.finances.service;

import com.mihmih.finances.model.Income;
import com.mihmih.finances.repository.IncomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository repository;
    private final AppUserService appUserService;

    @Override
    public List<Income> findAll() {
        return repository.findAll();
    }

    @Override
    public Income findById(Long incomeId) {
        return repository.findById(incomeId)
                .orElseThrow(() -> new IllegalArgumentException(
                                "No income found with id: " + incomeId
                ));
    }

    @Override
    public void deleteIncomeById(Long incomeId) {
        repository.deleteById(incomeId);
    }

    @Override
    public Income updateIncome(Income income) {
        Income incomeToUpdate = repository.getOne(income.getId());

        incomeToUpdate.setDate(income.getDate());
        incomeToUpdate.setIncomeCategory(income.getIncomeCategory());
        incomeToUpdate.setMoney(income.getMoney());

        return repository.save(incomeToUpdate);
    }

    @Override
    public Income saveIncome(Income income, Long appUserId) {
        income.setAppUser(appUserService.getOne(appUserId));

        return repository.save(income);
    }
}
