package com.restaurant.service.restaurantservice.repositories;

import com.restaurant.service.restaurantservice.models.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    
}
