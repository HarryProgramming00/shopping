package org.harry.farmer.customer.repository.mapper;

import org.harry.farmer.customer.model.CustomerDto;
import org.harry.farmer.customer.repository.model.CustomerRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomerRecordMapper implements RowMapper<CustomerRecord> {
    public CustomerRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setCustomerId(UUID.fromString(rs.getString("id")));
        customerRecord.setName(rs.getString("name"));
        return customerRecord;
    }
}