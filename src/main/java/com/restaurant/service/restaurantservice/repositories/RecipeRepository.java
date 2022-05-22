package com.restaurant.service.restaurantservice.repositories;

import com.restaurant.service.restaurantservice.models.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
