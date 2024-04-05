package org.harry.farmer.customer.service;

import org.harry.farmer.customer.model.CustomerDto;
import org.harry.farmer.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void getWithResult() {

        UUID customerId = UUID.randomUUID();
        CustomerDto customerDto = CustomerDto.builder().name("Harry").customerId(customerId).build();
        when(customerRepository.get(customerId)).thenReturn(List.of(customerDto));

        Optional<CustomerDto> result = customerService.get(customerId);

        assertTrue(result.isPresent());
        assertEquals(result.get().getCustomerId(), customerId);

    }
}