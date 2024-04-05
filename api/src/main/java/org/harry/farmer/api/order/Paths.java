package org.harry.farmer.api.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Paths {

    public static final String CUSTOMER_ORDERS = "/customer/{customerId}";

}
