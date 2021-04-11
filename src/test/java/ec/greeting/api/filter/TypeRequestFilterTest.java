package ec.greeting.api.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.container.ContainerRequestContext;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class TypeRequestFilterTest {
    @InjectMocks
    private TypeRequestFilter typeRequestFilter;
    @Mock
    private ContainerRequestContext containerRequestContext;


    @Test
    public void filterWithGetMethod() {
        when(containerRequestContext.getMethod()).thenReturn("GET");
        typeRequestFilter.filter(containerRequestContext);
    }

    @Test
    public void filterWithPostMethod() {
        when(containerRequestContext.getMethod()).thenReturn("POST");
        typeRequestFilter.filter(containerRequestContext);
    }

    @Test
    public void filterWithHeadMethod() {
        when(containerRequestContext.getMethod()).thenReturn("HEAD");
        typeRequestFilter.filter(containerRequestContext);
    }

    @Test
    public void filterWithOtherMethod() {
        when(containerRequestContext.getMethod()).thenReturn("PUT");
        typeRequestFilter.filter(containerRequestContext);
    }


}