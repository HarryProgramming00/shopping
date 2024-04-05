package org.harry.farmer.order.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harry.farmer.api.order.Order;
import org.harry.farmer.order.api.model.CreateOrder;
import org.harry.farmer.order.repository.OrderRepository;
import org.harry.farmer.order.model.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.harry.farmer.api.order.Paths.CUSTOMER_ORDERS;
import static org.harry.farmer.order.api.OrderController.BASE_URI;

@RestController
@RequestMapping(BASE_URI)
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    public static final String BASE_URI = "/orders";
    public static final String CREATE = "/create";

    private final OrderRepository orderRepository;

    @PostMapping(CREATE)
    public UUID create(@Valid @RequestBody CreateOrder createOrder) {
        log.info("Create order {}", createOrder);
        return orderRepository.save(toOrderDto(createOrder));
    }

    @GetMapping(CUSTOMER_ORDERS)
    public List<Order> getOrders(@PathVariable UUID customerId) {
        log.info("Get orders for {}", customerId);
        return toOrders(orderRepository.get(customerId));
    }

    private OrderDto toOrderDto(CreateOrder createOrder){
        return OrderDto.builder()
                .customerId(createOrder.getCustomerId())
                .productId(createOrder.getProductId())
                .build();
    }

    private List<Order> toOrders(List<OrderDto> orderDtos){
        return orderDtos.stream().map(o->Order.builder()
                        .id(o.getId())
                        .customerId(o.getCustomerId())
                        .productId(o.getProductId())
                        .build())
                .collect(Collectors.toList());
    }

}
