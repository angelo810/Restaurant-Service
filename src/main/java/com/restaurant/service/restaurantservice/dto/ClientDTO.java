package com.restaurant.service.restaurantservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String password;
}
