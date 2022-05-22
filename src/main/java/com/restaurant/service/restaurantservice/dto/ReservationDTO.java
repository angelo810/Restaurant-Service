package com.restaurant.service.restaurantservice.dto;

import java.time.LocalDate;

public class ReservationDTO {

    private Long id;
    private LocalDate date;
    private String typeOfPayment;
    private float total;
    private short numberOfTable;  
    private String observation;  
}
