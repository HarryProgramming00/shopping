package org.harry.farmer.customer.orders;

import org.harry.farmer.api.order.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

import static org.harry.farmer.api.order.Paths.CUSTOMER_ORDERS;

@Service
public class OrderClient {

    @Value("${order.service.url}")
    private String orderService;

    private WebClient orderClient;

    public List<Order> getOrders(UUID customerId){

        orderClient =WebClient.create(orderService);
        List<Order> orders = orderClient.get().uri(CUSTOMER_ORDERS, customerId).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {}).block();
        return orders;
    }

}
