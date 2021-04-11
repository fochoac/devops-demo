package ec.greeting.api.ws;

import com.google.gson.Gson;
import ec.greeting.model.ws.JwtToken;
import ec.greeting.service.SecurityService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("public/echo")
public class EchoWs {


    @Inject
    private SecurityService securityService;
    @Inject
    private Gson gson;
    @Inject
    private Logger log;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("generateJwtToken")
    @Operation(summary = "Generate JWT TOKEN with 30 seconds to life", description = "JWT TOKEN")

    @APIResponses({
            @APIResponse(
                    description = "Generate JWT TOKEN with 30 seconds to life",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = JwtToken.class))
            )

    })
    public Response generateJwtToken() {
        Response response;
        try {
            String token = securityService.generateJWTToken();
            JwtToken jwtToken = new JwtToken();
            jwtToken.setToken(token);
            response = Response.ok(gson.toJson(jwtToken)).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            response = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return response;

    }
}
