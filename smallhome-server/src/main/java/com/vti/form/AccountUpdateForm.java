package com.vti.form;

import com.vti.entity.Account;
import com.vti.validation.AccountExistsById;
import com.vti.validation.AccountNotExistsByUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateForm {

    @NotNull(message = "{AccountForm.id.NotNull}")
    @AccountExistsById
    private Integer id;

    @NotBlank(message = "{AccountForm.password.NotBlank}")
    @Length(max = 32, message = "{AccountForm.password.Length}")
    private String password;

    @NotBlank(message = "{AccountForm.name.NotBlank}")
    @Length(max = 50, message = "{AccountForm.name.Length}")
    private String name;

    @NotBlank(message = "{AccountForm.address.NotBlank}")
    @Length(max = 1000, message = "{AccountForm.address.Length}")
    private String address;

    @NotBlank(message = "{AccountForm.phone.NotBlank}")
    @Length(max = 10, message = "{AccountForm.phone.Length}")
    private String phone;

    @NotNull(message = "{AccountForm.role.NotNull}")
    private Account.Role role;

    @NotNull(message = "{AccountForm.status.NotNull}")
    private Boolean status;
}
