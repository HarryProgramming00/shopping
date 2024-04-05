package org.harry.farmer.customer.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.harry.farmer.api.customer.Customer;
import org.harry.farmer.customer.orders.IOrderService;
import org.harry.farmer.customer.model.CustomerDto;
import org.harry.farmer.customer.service.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
@ContextConfiguration(classes = {CustomerController.class})
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICustomerService customerService;
    @MockBean
    private IOrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getCustomer() throws Exception {
        UUID CUSTOMER_ID = UUID.randomUUID();
        CustomerDto customerExpected = CustomerDto.builder().customerId(CUSTOMER_ID).name("Harry").build();
        when(customerService.get(any())).thenReturn(Optional.ofNullable(customerExpected));

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(CustomerController.BASE_URI + CustomerController.GET, CUSTOMER_ID))
                .andReturn();

        Customer customerResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertEquals(customerExpected.getCustomerId(), customerResult.getId());
        assertEquals(customerExpected.getName(), customerResult.getName());
    }

    //TODO Add other tests



}
