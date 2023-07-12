package com.vti.specification;

import com.vti.entity.*;
import com.vti.form.OrderFilterForm;
import com.vti.form.ProductFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class OrderSpecification {
    public static Specification<Order> buildWhere(OrderFilterForm form) {
        if (form == null) {
            return null;
        }
        return null;}

    private static Specification<Order> hasUsernameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Order_.account).get(Account_.username), "%" + search.trim() + "%");
        };
    }

    private static Specification<Order> hasProductNameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Order_.product).get(Product_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Order> hasAccountIdEqual(Integer accountId) {
        return (root, query, builder) -> {
            if (accountId == null) {
                return null;
            }
            return builder.equal(root.get(Order_.account).get(Account_.id), accountId);
        };
    }

    private static Specification<Order> hasProductIdEqual(Integer productId) {
        return (root, query, builder) -> {
            if (productId == null) {
                return null;
            }
            return builder.equal(root.get(Order_.product).get(Product_.id), productId);
        };
    }
}
