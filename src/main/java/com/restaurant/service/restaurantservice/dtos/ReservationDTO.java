package com.restaurant.service.restaurantservice.dtos;

import java.time.LocalDate;

public class ReservationDTO {
    private Long id;
    private LocalDate date;
    private String typeOfPayment;
    private Float totalReservation;
    private int numberOfTable;
}
