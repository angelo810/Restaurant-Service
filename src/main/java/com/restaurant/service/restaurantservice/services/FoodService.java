package com.restaurant.service.restaurantservice.services;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.FoodDTO;
import com.restaurant.service.restaurantservice.dto.NewFoodDTO;

public interface FoodService {
    
    public FoodDTO create(NewFoodDTO foodDTO);
    public FoodDTO retrieve(Long id) throws Exception;
    public FoodDTO update(FoodDTO foodDTO, Long id) throws Exception;
    public void delete(Long id) throws Exception;

    public List<FoodDTO> list();
}


