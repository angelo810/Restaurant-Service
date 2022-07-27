package com.restaurant.service10.restaurantservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.service10.restaurantservice.dtos.FoodDTO;
import com.restaurant.service10.restaurantservice.dtos.FoodListDTO;
import com.restaurant.service10.restaurantservice.dtos.NewFoodDTO;
import com.restaurant.service10.restaurantservice.exceptions.NoContentException;
import com.restaurant.service10.restaurantservice.exceptions.ResourceNotFoundException;
import com.restaurant.service10.restaurantservice.models.Food;

import com.restaurant.service10.restaurantservice.repositories.FoodRepository;

import com.restaurant.service10.restaurantservice.services.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
    final ModelMapper modelMapper;
    final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository r,  ModelMapper m)
    {
        this.modelMapper = m;
        this.foodRepository = r;
    }

    @Override
    @Transactional
    public FoodDTO create(NewFoodDTO foodDTO) {
        Food food = modelMapper.map(foodDTO, Food.class);
        foodRepository.save(food);        
        return modelMapper.map(food, FoodDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public FoodDTO retrieve(Long id) {
        Food food = foodRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Food not found"));
        return modelMapper.map(food, FoodDTO.class);
    }


    @Override
    @Transactional
    public FoodDTO update(FoodDTO foodDTO, Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food not found"));        
              
        Food orderUpdated = modelMapper.map(foodDTO, Food.class);
        //Keeping values
        orderUpdated.setCreatedBy(food.getCreatedBy());
        orderUpdated.setCreatedDate(food.getCreatedDate());
        foodRepository.save(orderUpdated);   
        return modelMapper.map(orderUpdated, FoodDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food not found"));        
        foodRepository.deleteById(food.getId());        
    }
    @Override
    @Transactional(readOnly = true)
    public List<FoodListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Food> foods = foodRepository.findAll(pageable);
        if(foods.isEmpty()) throw new NoContentException("Foods is empty");
        return foods.stream().map(food -> modelMapper.map(food, FoodListDTO.class))
            .collect(Collectors.toList());        
    }
    @Override
    public long count() {        
        return foodRepository.count();
    }
    
}
