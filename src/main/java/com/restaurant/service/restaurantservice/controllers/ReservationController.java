package com.restaurant.service.restaurantservice.controllers;

import java.util.List;

import com.restaurant.service.restaurantservice.dto.NewReservationDTO;
import com.restaurant.service.restaurantservice.dto.ReservationDTO;
import com.restaurant.service.restaurantservice.services.ReservationService;

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
@RequestMapping("/reservations")

public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService srv){
        this.service = srv;
    }
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody NewReservationDTO reservationDTO){
        try {
            ReservationDTO result = service.create(reservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long id){
        try {
            ReservationDTO result = service.retrieve(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @GetMapping()
    public ResponseEntity<?> list(){
        try {
            List<ReservationDTO> result = service.list();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ReservationDTO reservationDTO, @PathVariable("id") Long id){
        try {
            ReservationDTO result = service.update(reservationDTO,  id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            service.delete(id);
            return ResponseEntity.ok().body("Reservation  Deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
