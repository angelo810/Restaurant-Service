package com.restaurant.service.restaurantservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodDTO {
    private Long id;
    private String name;
    private Float price;
    private String category;
}
