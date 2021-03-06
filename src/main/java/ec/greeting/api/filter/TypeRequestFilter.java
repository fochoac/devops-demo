package ec.greeting.api.filter;

import ec.greeting.util.ResourceBundleUtil;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@PreMatching
public class TypeRequestFilter implements ContainerRequestFilter {
    private static final List<String> BLACK_METHOD_LIST = Arrays.asList("PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE", "PATCH");

    private boolean hasValidHttpMethod(String method) {
        return BLACK_METHOD_LIST.stream()
                .noneMatch(blockHttpMethod -> method.toUpperCase().equals(blockHttpMethod));
    }


    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        if (!hasValidHttpMethod(containerRequestContext.getMethod())) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity(ResourceBundleUtil.getInstance().getMessage("ws.filter.message.http.method.deny"))
                            .build());
        }
    }
}
