package com.vti.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {
    private int id;

    private  String username;

    private String name;

    private String address;

    private Double phone;

    private String role;
}
