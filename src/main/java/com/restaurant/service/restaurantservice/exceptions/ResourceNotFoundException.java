package com.restaurant.service.restaurantservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(final String message){
        super(message);
    }

    
}
