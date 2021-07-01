package com.mihmih.finances.controller;

import com.mihmih.finances.model.PaymentCategory;
import com.mihmih.finances.model.api.PaymentCategoryResponse;
import com.mihmih.finances.service.PaymentCategoryService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
@AllArgsConstructor
public class PaymentCategoryController {

    private final PaymentCategoryService paymentCategoryService;

    @GetMapping("/{userId}")
    public List<PaymentCategoryResponse> getCategoriesByUserId(@PathVariable("userId") Long userId) {
        var paymentCategories =  paymentCategoryService.findAllByUserId(userId);

        return paymentCategories
                .stream()
                .map(PaymentCategoryResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}")
    public PaymentCategoryResponse addPaymentCategory(@PathVariable("userId") Long userId,
                                                      @RequestBody PaymentCategory paymentCategory) {

        return new PaymentCategoryResponse(
                paymentCategoryService.save(paymentCategory, userId)
        );
    }
}
