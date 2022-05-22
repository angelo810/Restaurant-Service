package com.restaurant.service.restaurantservice.repositories;

import com.restaurant.service.restaurantservice.models.Client;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
