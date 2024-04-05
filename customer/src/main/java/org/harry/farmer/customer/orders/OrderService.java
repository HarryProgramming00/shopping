package org.harry.farmer.customer.orders;

import lombok.RequiredArgsConstructor;
import org.harry.farmer.api.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderClient orderClient;

    public List<Order> getOrders(UUID customerId){
        return orderClient.getOrders(customerId);
    }
}
