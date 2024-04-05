package org.harry.farmer.customer.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harry.farmer.customer.model.CustomerDto;
import org.harry.farmer.customer.repository.mapper.CustomerRecordMapper;
import org.harry.farmer.customer.repository.model.CustomerRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT = "INSERT INTO customer VALUES (?, ?)";
    private final String QUERY = "SELECT * FROM CUSTOMER WHERE ID = ? ";

    public UUID save(CustomerDto customerDto){
        log.info("Saving {}", customerDto);
        UUID customerId = UUID.randomUUID();
        CustomerRecord customerRecord = toCustomerRecord(customerDto);
        jdbcTemplate.update(INSERT, customerId, customerRecord.getName());
        return customerId;
    }

    public List<CustomerDto> get(UUID customerId){
        log.info("Getting {}", customerId);
        List<CustomerRecord> customerRecords = jdbcTemplate.query(QUERY,new CustomerRecordMapper(), customerId);
        return customerRecords.stream().map(c->CustomerDto.builder().customerId(c.getCustomerId()).name(c.getName()).build()).collect(Collectors.toList());
    }

    private CustomerRecord toCustomerRecord(CustomerDto customerDto){
        return CustomerRecord.builder()
                .name(customerDto.getName())
                .customerId(customerDto.getCustomerId())
                .build();
    }
}
