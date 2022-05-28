package com.restaurant.service.restaurantservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewClientDTO {
    private String name;
    private String phone;
    private String mail;
    private String password;
}
