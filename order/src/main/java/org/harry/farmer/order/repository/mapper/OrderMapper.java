package org.harry.farmer.order.repository.mapper;

import org.harry.farmer.order.model.OrderDto;
import org.harry.farmer.order.repository.model.OrderRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OrderMapper implements RowMapper<OrderRecord> {
    public OrderRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(UUID.fromString(rs.getString("id")));
        orderRecord.setCustomerId(UUID.fromString(rs.getString("customer_id")));
        orderRecord.setProductId(UUID.fromString(rs.getString("product_id")));
        return orderRecord;
    }
}