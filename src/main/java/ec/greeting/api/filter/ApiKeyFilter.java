package ec.greeting.api.filter;
import ec.greeting.enumeration.ParameterEnum;
import ec.greeting.service.SecurityService;
import ec.greeting.util.ResourceBundleUtil;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.Optional;

public class ApiKeyFilter implements ContainerRequestFilter  {
    @Inject
    private SecurityService securityService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        if(containerRequestContext.getUriInfo().getAbsolutePath().toString().toLowerCase().contains("public")){
            return;
        }
        final String restApiKey = containerRequestContext.getHeaders().getFirst(ParameterEnum.API_KEY_PARSE_REST.getValue());
        final String jwtKhyToken = containerRequestContext.getHeaders().getFirst(ParameterEnum.API_KEY_JWT_KWY.getValue());
        boolean isValidRestApiKey = validateRestApiKey(restApiKey);
        boolean isValidJwtToken = securityService.isValidJWTToken(jwtKhyToken);
        if (!isValidJwtToken || !isValidRestApiKey) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(ResourceBundleUtil.getInstance().getMessage("ws.filter.message.not.api.key"))
                            .build());
        }

    }

    private boolean validateRestApiKey(String restApiKey) {
        return Optional.ofNullable(restApiKey)
                .filter(t -> Objects.nonNull(t)
                        && !t.trim().isEmpty()
                        && t.equals(ParameterEnum.API_KEY_PARSE_REST_DEFAUTL_VALUE))
                .isPresent();
    }
}
