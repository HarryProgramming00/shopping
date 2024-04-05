package org.harry.farmer.order.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRecord {

    private UUID id;
    private UUID customerId;
    private UUID productId;

}
