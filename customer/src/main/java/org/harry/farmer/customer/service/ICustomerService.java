package org.harry.farmer.customer.service;

import org.harry.farmer.customer.model.CustomerDto;

import java.util.Optional;
import java.util.UUID;

public interface ICustomerService {

    Optional<CustomerDto> get(UUID customerId);

    UUID create(CustomerDto customerDto);

}
