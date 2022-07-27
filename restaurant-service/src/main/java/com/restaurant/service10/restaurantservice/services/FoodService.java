package com.restaurant.service10.restaurantservice.services;

import java.util.List;

import com.restaurant.service10.restaurantservice.dtos.FoodDTO;
import com.restaurant.service10.restaurantservice.dtos.FoodListDTO;
import com.restaurant.service10.restaurantservice.dtos.NewFoodDTO;

public interface FoodService {
    public FoodDTO create(NewFoodDTO foodDTO);
    public FoodDTO retrieve(Long id);
    public FoodDTO update(FoodDTO foodDTO, Long id);
    public void delete(Long id);
    public long count();

    public List<FoodListDTO> list(int page, int size, String sort);
}
