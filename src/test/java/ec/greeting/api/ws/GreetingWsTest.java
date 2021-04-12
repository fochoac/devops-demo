package ec.greeting.api.ws;

import com.google.gson.Gson;
import ec.greeting.common.CommonTest;
import ec.greeting.model.ws.Greeting;
import ec.greeting.service.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Gson.class)
public class GreetingWsTest implements CommonTest {
    @InjectMocks
    private GreetingWs greetingWs;
    @Mock
    private Logger logger;
    @Mock
    private GreetingService greetingService;


    @Before
    public void setup() {
        Mockito.when(greetingService.replyGreetingWith(Mockito.any(Greeting.class))).thenReturn(new Greeting());
    }

    @Test
    public void replyGreetingWithError() {
        Gson gsonMock = PowerMockito.mock(Gson.class);
        PowerMockito.when(gsonMock.toJson(Mockito.any())).thenReturn("JSON");
        Response response = greetingWs.replyGreetingWith(getGreetingTemplate());
        assertNotNull(response);
    }
}