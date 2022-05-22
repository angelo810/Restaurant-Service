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
@Table(name="TBL_RESERVATION")
@Getter
@Setter
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "DATE")    
    private LocalDate date;
    @Column(name = "TYPER_OF_PAYMENT")    
    private String typeOfPayment;
    @Column(name = "TOTAL")    
    private float total;
    @Column(name = "NUMBER_OF_TABLE")    
    private short numberOfTable;
    @Column(name = "OBSERVATION")    
    private String observation;  
    
    @ManyToOne
    @JoinColumn(name="CLIENT_ID", nullable=false)
    private Client client; 

    @OneToMany(mappedBy="reservation") //nombre del atributo en la clase B       
    private List<Order> orders;
}