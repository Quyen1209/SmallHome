package com.vti.specification;

import com.vti.entity.Account;
import com.vti.entity.Account_;
import com.vti.form.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification {
    public static Specification<Account> buildWhere(AccountFilterForm form) {
        if (form == null) {
            return null;
        }
        return hasUsernameLike(form.getSearch())
                .or(hasNameLike(form.getSearch()))
                .and(hasRoleEqual(form.getRole()))
                .and(hasPhoneEqual(form.getPhone()));
    }

    private static Specification<Account> hasUsernameLike(String search) {
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (!StringUtils.hasText(search)) {
                    return null;
                }
                return builder.like(root.get(Account_.username),"%"+search.trim()+"%");
            }
        };
    }
//    public static Specification<Account> hasUsernameLike(String value) {
//        return (root, query, builder) -> {
//            if (!StringUtils.hasText(value)) {
//                return null;
//            }
//            return builder.like(root.get(Account_.username), "%" + value.trim() +"%");
//        };
//    }


    public static Specification<Account> hasNameLike(String search) {
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (!StringUtils.hasText(search)) {
                    return null;
                }
                return builder.like(root.get(Account_.name), "%" + search.trim() +"%");
            }
        };
    }

//    public static Specification<Account> hasNameLike(String value) {
//        return (root, query, builder) -> {
//            if (!StringUtils.hasText(value)) {
//                return null;
//            }
//            return builder.like(root.get(Account_.name), "%" + value.trim() +"%");
//        };
//    }

    public static Specification<Account> hasRoleEqual(Account.Role role) {
        return (root, query, builder) -> {
            if (role == null) {
                return null;
            }
            return builder.equal(root.get(Account_.role), role);
        };
    }

    public static Specification<Account> hasPhoneEqual(Double phone) {
        return (root, query, builder) -> {
            if (phone == null) {
                return null;
            }
            return builder.equal(root.get(Account_.phone), phone);
        };
    }

}
