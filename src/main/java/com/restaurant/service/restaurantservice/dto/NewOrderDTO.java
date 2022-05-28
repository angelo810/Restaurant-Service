package com.restaurant.service.restaurantservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewOrderDTO {
    private String description;
    private String waiter;
    private String statusOfOrder;
}
