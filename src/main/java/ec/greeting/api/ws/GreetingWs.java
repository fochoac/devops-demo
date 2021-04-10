package ec.greeting.api.ws;

import com.google.gson.Gson;
import ec.greeting.model.ws.Greeting;
import ec.greeting.service.GreetingService;
import ec.greeting.service.SecurityService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("greeting")
public class GreetingWs {

    @Inject
    private Logger log;
    @Inject
    private Gson gson;
    @Inject
    private SecurityService securityService;
    @Inject
    private GreetingService greetingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("DevOps")
    @Operation(summary = "Reply greeting by request", description = "Reply greeting by request.")

    @APIResponses({
            @APIResponse(
                    description = "Reply greeting by request",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Greeting.class))
            ),
            @APIResponse(
                    description = "Complete requiered parameters",
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = Greeting.class))
            )
    })
    public Response replyGreetingWith(@Context HttpHeaders httpHeaders, Greeting greetingRequest) {
        Response response;
        try {
            if (!hasTokensValids(httpHeaders)) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            Greeting reply = greetingService.replyGreetingWith(greetingRequest);
            response = Response.ok(gson.toJson(reply)).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            response = Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return response;

    }

    private boolean hasTokensValids(HttpHeaders httpHeaders) {
        List<String> valuesRestApiKey = httpHeaders.getRequestHeader("X-Parse-REST-API-Key");
        if (Objects.isNull(valuesRestApiKey) || valuesRestApiKey.isEmpty()) {
            return false;
        }
        String restApiKey = valuesRestApiKey.get(0);
        boolean hasRestApiKeyValid = Optional.ofNullable(restApiKey)
                .filter(t -> Objects.nonNull(t) && !t.trim().isEmpty() && t.equals("2f5ae96c-b558-4c7b-a590-a501ae1c3f6c"))
                .isPresent();
        List<String> valuesJwtKhy = httpHeaders.getRequestHeader("X-JWT-KWY");
        if (Objects.isNull(valuesJwtKhy) || valuesJwtKhy.isEmpty()) {
            return false;
        }
        String jwtKhyToken = valuesJwtKhy.get(0);
        log.info(jwtKhyToken);

        boolean hasValidJwtToken = Optional.ofNullable(jwtKhyToken)
                .filter(t -> Objects.nonNull(t) && !t.trim().isEmpty() && securityService.isValidJWTToken(jwtKhyToken))
                .isPresent();
        return hasRestApiKeyValid && hasValidJwtToken;

    }
}
