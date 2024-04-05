package org.harry.farmer.customer.service;

import lombok.RequiredArgsConstructor;
import org.harry.farmer.customer.model.CustomerDto;
import org.harry.farmer.customer.repository.CustomerRepository;
import org.harry.farmer.customer.repository.exception.DuplicateCustomerException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Optional<CustomerDto> get(UUID customerId) {
        List<CustomerDto> customerList = customerRepository.get(customerId);
        if (customerList.size()>1){
            throw new DuplicateCustomerException(customerId);
        }
        if(customerList.size()==1) {
            return Optional.of(customerList.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public UUID create(CustomerDto customerDto) {
        return customerRepository.save(customerDto);
    }
}
