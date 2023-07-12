package com.vti.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends RepresentationModel<ProductDTO> {
    private int id;
    private String name;
    private String description;
    private String thumbnailUrl;
    private double quantity;
    private double soldQuantity;
    private double price;
    private double salePrice;
    private Boolean status;
    private LocalDate createdDate;
    private CategoryDTO category;
    private List<OrderDTO> orders;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CategoryDTO extends RepresentationModel<CategoryDTO> {
        private int id;
        private String name;
        private Boolean status;
        private LocalDate createdDate;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderDTO extends RepresentationModel<OrderDTO> {
        private int id;
        private double count;
        private double totalPrice;
        private Boolean status;
    }
}
