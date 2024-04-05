package org.harry.farmer.customer.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harry.farmer.api.customer.Customer;
import org.harry.farmer.api.order.CustomerOrders;
import org.harry.farmer.api.order.Order;
import org.harry.farmer.customer.api.model.CreateCustomer;
import org.harry.farmer.customer.api.model.UpdateCustomer;
import org.harry.farmer.customer.exception.InvalidCustomerException;
import org.harry.farmer.customer.model.CustomerDto;
import org.harry.farmer.customer.orders.IOrderService;
import org.harry.farmer.customer.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.harry.farmer.customer.api.CustomerController.BASE_URI;

@RestController
@RequestMapping(BASE_URI)
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    public static final String BASE_URI = "/customer";
    public static final String CREATE = "/create";
    public static final String GET = "/{customerId}";
    public static final String UPDATE = "/update/{customerId}";
    public static final String DELETE = "/delete/{customerId}";
    public static final String GET_ORDERS = "/getOrders/{customerId}";

    private final ICustomerService customerService;
    private final IOrderService orderService;

    @PostMapping(CREATE)
    public UUID create(@Valid @RequestBody CreateCustomer createCustomer) {
        log.info("Create customer {}", createCustomer.getName());
        return customerService.create(toCustomerDto(createCustomer));
    }

    @GetMapping(GET)
    public Customer get(@PathVariable UUID customerId) {
        log.info("Get customer for {}", customerId);
        return toCustomer(customerService.get(customerId).get());
    }

    @PutMapping(UPDATE)
    public void update(@Valid @RequestBody UpdateCustomer updateCustomer) {
        //TODO implement
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable UUID customerId) {
        //TODO Implement
    }

    @GetMapping(GET_ORDERS)
    public List<CustomerOrders> getCustomerOrders(@PathVariable UUID customerId) {
        List<Order> orders = orderService.getOrders(customerId);
        Optional<CustomerDto> customer = customerService.get(customerId);
        if (customer.isEmpty()){
            throw new InvalidCustomerException(customerId);
        }

        return orders.stream().map(o->CustomerOrders.builder()
                .customerId(customerId)
                .customerName(customer.get().getName())
                .productId(o.getProductId())
                .productName("TODO retrieve product name...")
                .build()).collect(Collectors.toList());
    }

    private Customer toCustomer(CustomerDto customer) {
        return Customer.builder()
                .name(customer.getName()).id(customer.getCustomerId()).build();
    }

    private CustomerDto toCustomerDto(CreateCustomer customer){
        return CustomerDto.builder().name(customer.getName()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCustomerException.class)
    public String getError(RuntimeException exception){
        return exception.getMessage();
    }

}
