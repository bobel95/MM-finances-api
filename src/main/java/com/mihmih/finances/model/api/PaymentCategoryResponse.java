package com.mihmih.finances.model.api;

import com.mihmih.finances.model.PaymentCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCategoryResponse {

    private String category;
    private Long userId;
    private Long id;

    public PaymentCategoryResponse(PaymentCategory paymentCategory) {
        this.category = paymentCategory.getCategory();
        this.userId = paymentCategory.getAppUser().getId();
        this.id = paymentCategory.getId();
    }
}
