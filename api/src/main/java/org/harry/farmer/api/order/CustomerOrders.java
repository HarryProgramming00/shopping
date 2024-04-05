package org.harry.farmer.api.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrders {

    private UUID orderId;
    private UUID customerId;
    private String customerName;
    private UUID productId;
    private String productName;

}
