package com.restaurant.service.restaurantservice.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private LocalDate date;
    private String typeOfPayment;
    private Float totalReservation;
    private int numberOfTable;
}
