package com.vti.dto;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO extends RepresentationModel<AccountDTO> {

    private Integer id;

    private  String username;

    private String password;

    private String name;

    private String address;

    private String phone;

    private Account.Role role;

    private Boolean status;

    private LocalDate createdDate;

    private List<OrderDTO> orders;

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
