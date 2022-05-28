package com.restaurant.service.restaurantservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.restaurant.service.restaurantservice.dto.FoodDTO;
import com.restaurant.service.restaurantservice.dto.NewFoodDTO;
import com.restaurant.service.restaurantservice.models.Food;
import com.restaurant.service.restaurantservice.repositories.FoodRepository;
import com.restaurant.service.restaurantservice.services.FoodService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodServiceImpl implements FoodService {

    final ModelMapper modelMapper;
    final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(@Autowired FoodRepository repository, ModelMapper mapper){
        this.foodRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public FoodDTO create(NewFoodDTO foodDTO) {
        Food food = modelMapper.map(foodDTO, Food.class);
        foodRepository.save(food);
        FoodDTO foodDTOCreated = modelMapper.map(food, FoodDTO.class); 
        return foodDTOCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public FoodDTO retrieve(Long id) throws Exception {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()){
            throw new Exception("Food not found");
        }
        //.orElseThrow(()-> new Exception("Food not found"));
        return modelMapper.map(food.get(), FoodDTO.class);
    }

    @Override
    @Transactional
    public FoodDTO update(FoodDTO foodDTO, Long id) throws Exception {
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new Exception("Food not found"));
        food.setId(id);
        food = modelMapper.map(foodDTO, Food.class);
        foodRepository.save(food);       

        return modelMapper.map(food, FoodDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new Exception("Food not found"));        
        foodRepository.deleteById(food.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodDTO> list() {
        List<Food> foods = foodRepository.findAll();
        return foods.stream().map(food -> modelMapper.map(food, FoodDTO.class))
            .collect(Collectors.toList());        
    }
    
}


