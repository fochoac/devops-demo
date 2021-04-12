package ec.greeting.service;

import ec.greeting.common.CommonTest;
import ec.greeting.enumeration.ParameterEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.logging.Logger;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.crypto.*")
public class SecurityServiceTest implements CommonTest {
    @InjectMocks
    private SecurityService service;
    @Mock
    private Logger log;

    @Test
    public void generateJWTToken() {
        Assert.assertNotNull(service.generateJWTToken());
    }

    @Test
    public void validateJWTTokenInvalid() {
        Assert.assertFalse(service.isValidJWTToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJaTk5IcXNuWWtkIiwiaXNzIjoiYXV0aDAiLCJleHAiOjE2MTgwODk1MjUsInVzZXIiOiI1YjYzODMxYy1kYWNiLTQ5NDEtYTg2ZS01M2UwMDVlNDdlMWIiLCJpYXQiOjE2MTgwODk0OTV9.LF-EPoZi35yDisukU0kYlsqX718in-Agnzeg7hstFjA"));
    }

    @Test
    public void validateJWTTokenOk() {
        String jwtToken = service.generateJWTToken();
        Assert.assertTrue(service.isValidJWTToken(jwtToken));
    }

    @Test
    public void validateRestApiKeyOk() {
        Assert.assertTrue(service.isValidRestApiKey(ParameterEnum.API_KEY_PARSE_REST_DEFAUTL_VALUE.getValue()));
    }

    @Test
    public void validateRestApiKeyInvalid() {
        Assert.assertFalse(service.isValidRestApiKey("test"));
    }
}