package com.restaurant.service.restaurantservice.repositories;

import com.restaurant.service.restaurantservice.models.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    
}
