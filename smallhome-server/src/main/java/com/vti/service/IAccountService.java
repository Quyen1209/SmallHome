package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.form.AuthChangePassword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    // Get list account
    Page<Account> findAll(Pageable pageable, AccountFilterForm form);

    // Get account by id
    Account findById(int id);

    // Create Account
    void create(AccountCreateForm form);

    // Update account
    void update(AccountUpdateForm form);

    // Delete buy id
    void deleteById(int id);

    // exists by id
    boolean existsById(int id);

//    // Security
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    // find account by username
    Account findByUsername(String username);

    // delete all accounts by list id
    void deleteAll(List<Integer> ids);

//    // change password
    void changePassword(AuthChangePassword form);
}
