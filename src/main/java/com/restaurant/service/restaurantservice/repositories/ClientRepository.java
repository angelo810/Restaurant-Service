package com.restaurant.service.restaurantservice.repositories;

import java.util.List;

import com.restaurant.service.restaurantservice.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    
    public List<Client> findByTitle(String criteria);
}
