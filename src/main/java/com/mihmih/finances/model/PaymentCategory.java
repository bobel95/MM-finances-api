package com.mihmih.finances.model;

import com.fasterxml.jackson.annotation.*;
import com.mihmih.finances.model.api.AppUserResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "payment_category",
        uniqueConstraints=
        @UniqueConstraint(columnNames={
                "app_user_id",
                "category"}
        )
)
public class PaymentCategory {

    @Id
    @SequenceGenerator(name = "payment_category_sequence",
            sequenceName = "payment_category_sequence",
            allocationSize = 1)
    @GeneratedValue(
            generator = "payment_category_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String category;

//    @OneToMany(mappedBy = "paymentCategory", orphanRemoval = true, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties
//    private List<Payment> paymentList;

    @OneToOne()
//    @JoinColumn(name="app_user_id")
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("appUserId")
    @JsonIgnoreProperties({ "paymentList", "appUserRole", "enabled", "locked", "password", "incomeList", "authorities" })
    private AppUser appUser;

//    @JsonProperty("appUserId")
//    public void setAppUserById(Long appUserId) {
//        appUser = AppUser.fromId(appUserId);
//    }
//
//    public static PaymentCategory fromCategory(String category) {
//        PaymentCategory paymentCategory = new PaymentCategory();
//        paymentCategory.setCategory(category);
//        return paymentCategory;
//    }
}
