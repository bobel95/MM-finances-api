package com.mihmih.finances.controller;

import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.service.PaymentCategoryService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
@AllArgsConstructor
public class PaymentCategoryController {

    private final PaymentCategoryService paymentCategoryService;

    @GetMapping("/{userId}")
    public List<PaymentCategory> getCategoriesByUserId(
            @PathVariable("userId") Long userId) {
        return paymentCategoryService.findAllByUserId(userId);
    }
}
