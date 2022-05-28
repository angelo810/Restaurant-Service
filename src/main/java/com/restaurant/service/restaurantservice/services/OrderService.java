package com.restaurant.service.restaurantservice.services;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.NewOrderDTO;
import com.restaurant.service.restaurantservice.dto.OrderDTO;

public interface OrderService {
    public OrderDTO create(NewOrderDTO recipeDTO);
    public OrderDTO retrieve(Long id) throws Exception;
    public OrderDTO update(OrderDTO orderDTO, Long id) throws Exception;
    public void delete(Long id) throws Exception;

    public List<OrderDTO> list();
}
