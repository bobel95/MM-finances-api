package com.mihmih.finances.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mihmih.finances.model.AppUser;
import com.mihmih.finances.model.Income;
import com.mihmih.finances.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {

    public AppUserResponse(AppUser appUser) {
        this.id          = appUser.getId();
        this.firstName   = appUser.getFirstName();
        this.lastName    = appUser.getLastName();
        this.email       = appUser.getEmail();
        this.incomeList  = appUser.getIncomeList();
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnoreProperties( value = { "appUser" })
    private List<PaymentResponse> paymentList;
    @JsonIgnoreProperties( value = { "appUser" })
    private List<Income> incomeList;
}
