package com.vti.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductFilterForm {
    private String search;

    private Integer categoryId;

    private Double minSalePrice;

    private Double maxSalePrice;
}
