package org.harry.farmer.order.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harry.farmer.order.repository.mapper.OrderMapper;
import org.harry.farmer.order.model.OrderDto;
import org.harry.farmer.order.repository.model.OrderRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_ORDER = "INSERT INTO orders VALUES (?, ?, ?)";
    private final String SELECT_BY_CUSTOMER_ID = "SELECT * FROM orders where customerId = ?";

    public UUID save(OrderDto orderDto) {
        log.info("Saving {}", orderDto);
        UUID orderId = UUID.randomUUID();
        jdbcTemplate.update(INSERT_ORDER, orderId, orderDto.getCustomerId(), orderDto.getProductId());
        return orderId;
    }

    public List<OrderDto> get(UUID customerId) {
        log.info("Getting orders for customer {}", customerId);
        List<OrderRecord> orderRecords = jdbcTemplate.query(SELECT_BY_CUSTOMER_ID, new OrderMapper(), customerId);
        return toOrderDto(orderRecords);
    }

    private List<OrderDto> toOrderDto(List<OrderRecord> orderRecords){

        return orderRecords.stream().map(o->OrderDto.builder()
                        .id(o.getId())
                        .customerId(o.getCustomerId())
                        .productId(o.getProductId())
                        .build())
                .collect(Collectors.toList());

    }

}
