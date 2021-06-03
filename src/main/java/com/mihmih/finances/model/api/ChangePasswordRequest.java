package com.mihmih.finances.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    private String prevPassword;
    private String newPassword;
}
