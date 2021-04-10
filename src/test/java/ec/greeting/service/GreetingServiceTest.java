package ec.greeting.service;


import ec.greeting.model.ws.Greeting;
import ec.greeting.common.CommonTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class GreetingServiceTest implements CommonTest {

    @InjectMocks
    private GreetingService service;

    @Test(expected = NullPointerException.class)
    public void replyGreetingWithExceptioForRequieredFields() {
        Greeting greetingRequest = new Greeting();
        service.replyGreetingWith(greetingRequest);
    }

    @Test
    public void replyGreetingWithValidParameterFields() {
        Greeting response = service.replyGreetingWith(getGreetingTemplate());
        Assert.assertEquals(EXPECTED_GREETING_MESSAGE, response.getMessage());
    }


}