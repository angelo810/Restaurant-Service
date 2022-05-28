package com.restaurant.service.restaurantservice.repositories;

import java.util.List;

import com.restaurant.service.restaurantservice.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    
    public List<Order> findByTitle(String criteria);
}
