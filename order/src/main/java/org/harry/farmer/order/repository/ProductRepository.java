package org.harry.farmer.order.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harry.farmer.order.model.OrderDto;
import org.harry.farmer.order.model.ProductDto;
import org.harry.farmer.order.repository.mapper.OrderMapper;
import org.harry.farmer.order.repository.mapper.ProductMapper;
import org.harry.farmer.order.repository.model.OrderRecord;
import org.harry.farmer.order.repository.model.ProductRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT = "SELECT * FROM products";

    public List<ProductDto> getProducts() {
        log.info("Getting products");
        List<ProductRecord> productRecords = jdbcTemplate.query(SELECT, new ProductMapper());
        return toProductDto(productRecords);
    }

    private List<ProductDto> toProductDto(List<ProductRecord> productRecords){

        return productRecords.stream().map(p->ProductDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .unitPrice(p.getUnitPrice())
                        .build())
                .collect(Collectors.toList());

    }

}
