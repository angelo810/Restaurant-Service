package com.restaurant.service10.restaurantservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodListDTO {

    private Long id;
    private String name;
    private double price;
    private String category;
}
