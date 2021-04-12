package ec.greeting.api.filter;

import ec.greeting.enumeration.ParameterEnum;
import ec.greeting.service.SecurityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class ApiKeyFilterTest {

    @InjectMocks
    private ApiKeyFilter apiKeyFilter;
    @Mock
    private ContainerRequestContext containerRequestContext;
    @Mock
    private UriInfo uriInfo;
    @Mock
    private SecurityService securityService;

    @Before
    public void setup() throws URISyntaxException {
        loadHeaderValues();

        when(containerRequestContext.getUriInfo()).thenReturn(uriInfo);
        URI uri = new URI("http://api.com/v1/test");
        when(containerRequestContext.getUriInfo().getAbsolutePath()).thenReturn(uri);


    }

    @Test
    public void filterWithPublicRoute() throws URISyntaxException {
        URI uri = new URI("http://api.com/public/test");
        when(containerRequestContext.getUriInfo().getAbsolutePath()).thenReturn(uri);
        apiKeyFilter.filter(containerRequestContext);
        Assert.assertTrue(true);
    }

    @Test
    public void filterWithCorrectTokens() {

        when(securityService.isValidJWTToken(anyString())).thenReturn(true);
        when(securityService.isValidRestApiKey(anyString())).thenReturn(true);
        apiKeyFilter.filter(containerRequestContext);
        Assert.assertTrue(true);
    }

    @Test
    public void filterWithIncorrectJWTToken() {

        when(securityService.isValidJWTToken(anyString())).thenReturn(false);
        when(securityService.isValidRestApiKey(anyString())).thenReturn(true);
        apiKeyFilter.filter(containerRequestContext);
        Assert.assertTrue(true);
    }

    @Test
    public void filterWithIncorrectRestToken() {

        when(securityService.isValidJWTToken(anyString())).thenReturn(true);
        when(securityService.isValidRestApiKey(anyString())).thenReturn(false);
        apiKeyFilter.filter(containerRequestContext);
        Assert.assertTrue(true);
    }

    private void loadHeaderValues() {
        MultivaluedMap<String, String> headerMap = new MultivaluedHashMap<>();
        headerMap.add(ParameterEnum.API_KEY_PARSE_REST.getValue(), "API-REST-TOKEN");
        headerMap.add(ParameterEnum.API_KEY_JWT_KWY.getValue(), "JWT-TOKEN");
        when(containerRequestContext.getHeaders()).thenReturn(headerMap);
    }
}