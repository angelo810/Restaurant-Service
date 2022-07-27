package com.restaurant.service10.restaurantservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.service10.restaurantservice.dtos.FoodDTO;
import com.restaurant.service10.restaurantservice.dtos.FoodListDTO;
import com.restaurant.service10.restaurantservice.dtos.NewFoodDTO;
import com.restaurant.service10.restaurantservice.services.FoodService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/foods")
public class FoodController {
    
    final FoodService service;

    public FoodController(FoodService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<FoodDTO> create(@Valid @RequestBody NewFoodDTO foodDTO){
        FoodDTO result = service.create(foodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    /* ================ RETRIEVE ================ */
     @GetMapping("/{id}")
     public ResponseEntity<FoodDTO> retrive(@PathVariable("id") Long id){
        FoodDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
        }
    
    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> update(@RequestBody FoodDTO foodDTO, @PathVariable("id") Long id){
        FoodDTO result = service.update(foodDTO, id);
        return ResponseEntity.ok().body(result);
    }
    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<FoodListDTO>> list(@PathVariable("page") int page, 
        @PathVariable("size") int size,
        @RequestParam(name = "sort", required = false) String sort){
        List<FoodListDTO> result  = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);        
    }
    
     /* ================ COUNT ================ */
     @GetMapping("/count")
     public ResponseEntity<Long> count(){
         long result = service.count();
         return ResponseEntity.ok().body(result);        
     }
}



