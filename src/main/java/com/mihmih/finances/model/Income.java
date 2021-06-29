package com.mihmih.finances.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @SequenceGenerator(name = "income_sequence", sequenceName = "income_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_sequence")
    private Long id;

    @ApiModelProperty(example = " 'money' : { 'amount': 10, 'currency': 'RON'}")
    private Money money;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private IncomeCategory incomeCategory;

    @ManyToOne
    private AppUser appUser;
}
