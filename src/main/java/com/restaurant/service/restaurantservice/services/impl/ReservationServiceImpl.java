package com.restaurant.service.restaurantservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.restaurant.service.restaurantservice.dto.NewReservationDTO;
import com.restaurant.service.restaurantservice.dto.ReservationDTO;
import com.restaurant.service.restaurantservice.models.Reservation;
import com.restaurant.service.restaurantservice.repositories.ReservationRepository;
import com.restaurant.service.restaurantservice.services.ReservationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ReservationServiceImpl implements ReservationService {
    final ModelMapper modelMapper;
    final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(@Autowired ReservationRepository repository, ModelMapper mapper){
        this.reservationRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public ReservationDTO create(NewReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservationRepository.save(reservation);
        ReservationDTO reservationDTOCreated = modelMapper.map(reservation, ReservationDTO.class); 
        return reservationDTOCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationDTO retrieve(Long id) throws Exception {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new Exception("Reservation not found");
        }
        //.orElseThrow(()-> new Exception("Exam not found"));
        return modelMapper.map(reservation.get(), ReservationDTO.class);
    }

    @Override
    @Transactional
    public ReservationDTO update(ReservationDTO reservationDTO, Long id) throws Exception {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new Exception("Reservation not found"));
        reservation.setId(id);
        reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservationRepository.save(reservation);       

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new Exception("Reservation not found"));        
        reservationRepository.deleteById(reservation.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> list() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
            .collect(Collectors.toList());        
    }
}
