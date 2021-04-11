package ec.greeting.api.ws;

import com.google.gson.Gson;
import ec.greeting.service.SecurityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Gson.class)
public class EchoWsTest {
    @InjectMocks
    private EchoWs echoWs;
    @Mock
    private Logger logger;
    @Mock
    private SecurityService securityService;

    @Before
    public void setup() {
        Mockito.when(securityService.generateJWTToken()).thenReturn("JWT TOKEN");

    }

    @Test
    public void generateJwtTokenError() {
        Response response = echoWs.generateJwtToken();
        Assert.assertNotNull(response);

    }
}