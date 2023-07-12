package com.vti.form;

import com.vti.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private Account.Role role;
    private Double phone;
}
