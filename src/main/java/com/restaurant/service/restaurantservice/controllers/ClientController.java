package com.restaurant.service.restaurantservice.controllers;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.ClientDTO;
import com.restaurant.service.restaurantservice.dto.NewClientDTO;
import com.restaurant.service.restaurantservice.services.ClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final ClientService service;

    public ClientController(ClientService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody NewClientDTO clientDTO){
        try {
            ClientDTO result = service.create(clientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long id){
        try {
            ClientDTO result = service.retrieve(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @GetMapping()
    public ResponseEntity<?> list(){
        try {
            List<ClientDTO> result = service.list();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDTO clientDTO, @PathVariable("id") Long id){
        try {
            ClientDTO result = service.update(clientDTO,  id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            service.delete(id);
            return ResponseEntity.ok().body("Clienten Deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}


