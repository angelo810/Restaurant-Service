package com.restaurant.service.restaurantservice.services;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.ClientDTO;
import com.restaurant.service.restaurantservice.dto.NewClientDTO;

public interface ClientService {
    
    public ClientDTO create(NewClientDTO clientDTO);
    public ClientDTO retrieve(Long id) throws Exception;
    public ClientDTO update(ClientDTO clientDTO, Long id) throws Exception;
    public void delete(Long id) throws Exception;

    public List<ClientDTO> list();
}

