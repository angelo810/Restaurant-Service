package com.restaurant.service.restaurantservice.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_RESERVATIONS")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE") 
    private LocalDate date;

    @Column(name = "TYPE_OF_PAYMENT") 
    private String typeOfPayment;

    @Column(name = "TOTAL_RESERVATION") 
    private Float totalReservation;

    @Column(name = "NUMBER_OF_TABLE") 
    private int numberOfTable;

    @ManyToOne
    @JoinColumn(name="CLIENT_ID", nullable=false)
    private Client client;

    @OneToMany(mappedBy="reservation")       
    private List<Order> orders;
}
