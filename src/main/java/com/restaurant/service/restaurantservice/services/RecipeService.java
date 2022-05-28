package com.restaurant.service.restaurantservice.services;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.NewRecipeDTO;
import com.restaurant.service.restaurantservice.dto.RecipeDTO;

public interface RecipeService {
    public RecipeDTO create(NewRecipeDTO recipeDTO);
    public RecipeDTO retrieve(Long id) throws Exception;
    public RecipeDTO update(RecipeDTO recipeDTO, Long id) throws Exception;
    public void delete(Long id) throws Exception;

    public List<RecipeDTO> list();
}
