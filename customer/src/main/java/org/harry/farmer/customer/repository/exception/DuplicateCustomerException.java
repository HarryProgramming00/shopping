package org.harry.farmer.customer.repository.exception;

import java.util.UUID;

public class DuplicateCustomerException extends RuntimeException{

    public DuplicateCustomerException(UUID customerId){
        super(String.format("More than 1 customer found for UUID %s", customerId));
    }
}
