package com.restaurant.service.restaurantservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.restaurant.service.restaurantservice.dto.ClientDTO;
import com.restaurant.service.restaurantservice.dto.NewClientDTO;
import com.restaurant.service.restaurantservice.models.Client;
import com.restaurant.service.restaurantservice.repositories.ClientRepository;
import com.restaurant.service.restaurantservice.services.ClientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    final ModelMapper modelMapper;
    final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(@Autowired ClientRepository repository, ModelMapper mapper){
        this.clientRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public ClientDTO create(NewClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        ClientDTO clientDTOCreated = modelMapper.map(client, ClientDTO.class); 
        return clientDTOCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO retrieve(Long id) throws Exception {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new Exception("Client not found");
        }
        //.orElseThrow(()-> new Exception("Client not found"));
        return modelMapper.map(client.get(), ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO update(ClientDTO clientDTO, Long id) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new Exception("Client not found"));
        client.setId(id);
        client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);       

        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new Exception("Client not found"));        
        clientRepository.deleteById(client.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> list() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(client -> modelMapper.map(client, ClientDTO.class))
            .collect(Collectors.toList());        
    }
    
}


