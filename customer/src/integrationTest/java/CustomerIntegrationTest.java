import com.fasterxml.jackson.databind.ObjectMapper;
import org.harry.farmer.api.customer.Customer;
import org.harry.farmer.customer.CustomerServiceApp;
import org.harry.farmer.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CustomerServiceApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerService customerService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenCustomers_whenGetCustomer_thenRetrievedOk() throws Exception {

        String customerId = "c2c5ad6c-28a4-4ce3-a58f-9f2073e0a5b3";

        MvcResult result = mvc.perform(get("/customer/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Customer customer = objectMapper.readValue(result.getResponse().getContentAsString(), Customer.class);

        assertEquals("Harry Farmer",customer.getName());
        assertEquals(customerId,customer.getId().toString());
    }
}
