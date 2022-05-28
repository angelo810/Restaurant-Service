package com.restaurant.service.restaurantservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.restaurant.service.restaurantservice.dto.NewOrderDTO;
import com.restaurant.service.restaurantservice.dto.OrderDTO;
import com.restaurant.service.restaurantservice.models.Order;
import com.restaurant.service.restaurantservice.repositories.OrderRepository;
import com.restaurant.service.restaurantservice.services.OrderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImpl implements OrderService {
    final ModelMapper modelMapper;
    final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(@Autowired OrderRepository repository, ModelMapper mapper){
        this.orderRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public OrderDTO create(NewOrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderRepository.save(order);
        OrderDTO examDTOCreated = modelMapper.map(order, OrderDTO.class); 
        return examDTOCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO retrieve(Long id) throws Exception {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new Exception("Order not found");
        }
        //.orElseThrow(()-> new Exception("Exam not found"));
        return modelMapper.map(order.get(), OrderDTO.class);
    }

    @Override
    @Transactional
    public OrderDTO update(OrderDTO orderDTO, Long id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new Exception("Order not found"));
        order.setId(id);
        order = modelMapper.map(orderDTO, Order.class);
        orderRepository.save(order);       

        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new Exception("Order not found"));        
        orderRepository.deleteById(order.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> list() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class))
            .collect(Collectors.toList());        
    }
    
}