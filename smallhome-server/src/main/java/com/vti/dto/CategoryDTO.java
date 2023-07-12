package com.vti.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDTO extends RepresentationModel<CategoryDTO> {

    private int id;

    private String name;

    private Boolean status;

    private LocalDate createdDate;

    private List<ProductDTO> products;


    @Setter
    @Getter
    @NoArgsConstructor
    public static class ProductDTO extends RepresentationModel<ProductDTO> {
        private int Id;
        private String name;
        private String description;
        private String thumbnailUrl;
        private double quantity;
        private double soldQuantity;
        private double price;
        private double salePrice;
        private Boolean status;
        private LocalDate createdDate;
    }
}
