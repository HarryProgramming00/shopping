package org.harry.farmer.customer.orders;

import lombok.RequiredArgsConstructor;
import org.harry.farmer.api.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    List<Order> getOrders(UUID customerId);

}
