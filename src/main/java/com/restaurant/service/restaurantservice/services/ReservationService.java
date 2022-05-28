package com.restaurant.service.restaurantservice.services;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.NewReservationDTO;
import com.restaurant.service.restaurantservice.dto.ReservationDTO;

public interface ReservationService {
    public ReservationDTO create(NewReservationDTO reservationDTO);
    public ReservationDTO retrieve(Long id) throws Exception;
    public ReservationDTO update(ReservationDTO reservationDTO, Long id) throws Exception;
    public void delete(Long id) throws Exception;

    public List<ReservationDTO> list();
}
