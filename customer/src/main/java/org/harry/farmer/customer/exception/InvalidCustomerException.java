package org.harry.farmer.customer.exception;

import java.util.UUID;

public class InvalidCustomerException extends RuntimeException{

    public InvalidCustomerException(UUID customerId){
        super(String.format("Cannot find customer with id %s", customerId.toString()));
    }
}
