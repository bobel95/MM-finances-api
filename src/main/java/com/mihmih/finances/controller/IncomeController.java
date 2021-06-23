package com.mihmih.finances.controller;

import com.mihmih.finances.model.Income;
import com.mihmih.finances.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/income")
@AllArgsConstructor
public class IncomeController {
    private final IncomeService service;

    @GetMapping
    public List<Income> getAll() {
        return service.findAll();
    }

    @GetMapping("/{incomeId}")
    public Income findById(@PathVariable("incomeId") Long incomeId) {
        return service.findById(incomeId);
    }

    @PostMapping("/{appUserId}")
    public Income addIncome(
            @Valid @RequestBody Income income,
            @PathVariable("appUserId") Long appUserId) {
        return service.saveIncome(income, appUserId);
    }

    @PutMapping()
    public Income updateIncome(@Valid @RequestBody Income income) {
        return service.updateIncome(income);
    }

    @DeleteMapping("/{incomeId}")
    public void deleteIncomeById(@PathVariable("incomeId") Long incomeId) {
        service.deleteIncomeById(incomeId);
    }
}
