import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.harry.farmer.api.customer.Customer;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions extends SpringIntegrationTest {

    private Customer customer;

    @When("we retrieve customer {string}")
    public void getCustomer(String customerId) throws Throwable {
        customer = getCustomer(UUID.fromString(customerId));
    }

    @Then("we have retrieved customer with name {}")
    public void checkCustomer(String name) {
        assertEquals(name, customer.getName());
    }

}