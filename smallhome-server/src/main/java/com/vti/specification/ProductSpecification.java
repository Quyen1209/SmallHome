package com.vti.specification;

import com.vti.entity.Category_;
import com.vti.entity.Product;
import com.vti.entity.Product_;
import com.vti.form.ProductFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecification {
    public static Specification<Product> buildWhere(ProductFilterForm form) {
        if (form == null) {
            return null;
        }
        return hasNameLike(form.getSearch())
                .or(hasCategoryNameLike(form.getSearch()))
                .and(hasCategoryIdEqual(form.getCategoryId()))
                .and(hasSalePriceGreaterThanOrEqualTo(form.getMinSalePrice()))
                .and(hasSalePriceLessThanOrEqualTo(form.getMaxSalePrice()));}


    private static Specification<Product> hasNameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Product_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Product> hasCategoryNameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Product_.category).get(Category_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Product> hasCategoryIdEqual(Integer categoryId) {
        return (root, query, builder) -> {
            if (categoryId == null) {
                return null;
            }
            return builder.equal(root.get(Product_.category).get(Category_.id), categoryId);
        };
    }

    private static Specification<Product> hasSalePriceGreaterThanOrEqualTo(Double minSalePrice) {
        return (root, query, builder) -> {
            if (minSalePrice == null) {
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get(Product_.salePrice), minSalePrice);
        };
    }

    private static Specification<Product> hasSalePriceLessThanOrEqualTo(Double maxSalePrice) {
        return (root, query, builder) -> {
            if (maxSalePrice == null) {
                return null;
            }
            return builder.lessThanOrEqualTo(root.get(Product_.salePrice), maxSalePrice);
        };
    }
}
