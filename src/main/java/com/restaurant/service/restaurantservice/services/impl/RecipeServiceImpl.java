package com.restaurant.service.restaurantservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.restaurant.service.restaurantservice.dto.NewRecipeDTO;
import com.restaurant.service.restaurantservice.dto.RecipeDTO;
import com.restaurant.service.restaurantservice.models.Recipe;
import com.restaurant.service.restaurantservice.repositories.RecipeRepository;
import com.restaurant.service.restaurantservice.services.RecipeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class RecipeServiceImpl implements RecipeService{
    final ModelMapper modelMapper;
    final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(@Autowired RecipeRepository repository, ModelMapper mapper){
        this.recipeRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public RecipeDTO create(NewRecipeDTO recipeDTO) {
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
        recipeRepository.save(recipe);
        RecipeDTO recipeDTOCreated = modelMapper.map(recipe, RecipeDTO.class); 
        return recipeDTOCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeDTO retrieve(Long id) throws Exception {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isEmpty()){
            throw new Exception("Recipe not found");
        }
        //.orElseThrow(()-> new Exception("Exam not found"));
        return modelMapper.map(recipe.get(), RecipeDTO.class);
    }

    @Override
    @Transactional
    public RecipeDTO update(RecipeDTO recipeDTO, Long id) throws Exception {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(()-> new Exception("Recipe not found"));
        recipe.setId(id);
        recipe = modelMapper.map(recipeDTO, Recipe.class);
        recipeRepository.save(recipe);       

        return modelMapper.map(recipe, RecipeDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(()-> new Exception("Recipe not found"));        
        recipeRepository.deleteById(recipe.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDTO> list() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipe -> modelMapper.map(recipe, RecipeDTO.class))
            .collect(Collectors.toList());        
    }
}
