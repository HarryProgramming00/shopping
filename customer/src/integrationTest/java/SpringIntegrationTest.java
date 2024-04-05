import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.CucumberContextConfiguration;
import org.harry.farmer.api.customer.Customer;
import org.harry.farmer.customer.CustomerServiceApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerServiceApp.class)
@AutoConfigureMockMvc
public class SpringIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Customer getCustomer(UUID customerId) throws Exception {
        MvcResult result = mvc.perform(get("/customer/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Customer customer = objectMapper.readValue(result.getResponse().getContentAsString(), Customer.class);

        return customer;
    }

}
