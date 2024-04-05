package org.harry.farmer.order.repository.mapper;

import org.harry.farmer.order.repository.model.OrderRecord;
import org.harry.farmer.order.repository.model.ProductRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductMapper implements RowMapper<ProductRecord> {
    public ProductRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductRecord productRecord = new ProductRecord();
        productRecord.setId(UUID.fromString(rs.getString("id")));
        productRecord.setName(rs.getString("name"));
        productRecord.setUnitPrice(rs.getBigDecimal("unit_price"));
        return productRecord;
    }
}