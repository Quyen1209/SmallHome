package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.form.AuthChangePassword;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get list account
    @Override
    public Page<Account> findAll(Pageable pageable, AccountFilterForm form)
    {
        return repository.findAll(AccountSpecification.buildWhere(form),pageable);
    }

    // Get account by id
    @Override
    public Account findById(int id) {
        return repository.findById(id).orElse(null);
    }
    // Create Account
    @Override
    public void create(AccountCreateForm form) {
//        String hash = passwordEncoder.encode(form.getPassword());
        Account account = mapper.map(form,Account.class);
//        account.setPassword(hash);
        repository.save(account);
    }
    // Update account
    @Override
    public void update(AccountUpdateForm form) {
        repository.save(mapper.map(form,Account.class));
    }
    // Delete buy id
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
    // exists by id
    @Override
    public boolean existsById(int id) {
        return  repository.existsById(id);
    }

     // Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Account account = repository.findByUsername(username);
        if (account == null ){
            throw new UsernameNotFoundException(username);
        }
        return new User(
                account.getUsername(),
                account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole().toString())
        );
    }
     // find account by username
    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username);

    }

    // delete all accounts by list id
    @Override
    public void deleteAll(List<Integer> ids) {
        repository.deleteAllById(ids);
    }

     // change password
    @Override
    public  void changePassword(AuthChangePassword form) {
        repository.changePassword(form.getUsername(), passwordEncoder.encode(form.getPassword()));
    }
}
