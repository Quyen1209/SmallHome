package com.vti.dto;


import com.vti.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends RepresentationModel<OrderDTO> {
    private int id;
    private double count;
    private double totalPrice;
    private Boolean status;
    private AccountDTO account;
    private ProductDTO product;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AccountDTO extends RepresentationModel<AccountDTO> {
        private int id;
        private String username;
        private String password;
        private String name;
        private String address;
        private String phone;
        private Account.Role role;
        private Boolean status;
        private LocalDate createdDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductDTO extends RepresentationModel<ProductDTO> {
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
    }
}
