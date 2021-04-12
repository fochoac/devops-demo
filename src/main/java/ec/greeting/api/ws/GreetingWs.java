package ec.greeting.api.ws;

import com.google.gson.Gson;
import ec.greeting.model.ws.Greeting;
import ec.greeting.service.GreetingService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private GreetingService greetingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("DevOps")
    @SecurityRequirements({@SecurityRequirement(name = "X-Parse-REST-API-Key"), @SecurityRequirement(name = "X-JWT-KWY")})
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
                    content = @Content(example = "Field \"to\"  is requiered")
            ),
            @APIResponse(
                    description = "Requiered Header Api Keys",
                    responseCode = "401",
                    content = @Content(example = "X-Parse-REST-API-Key (STATIC TOKEN) and X-JWT-KWY (JWT TOKEN)")
            )
    })
    public Response replyGreetingWith(Greeting greetingRequest) {
        Response response;
        try {

            Greeting reply = greetingService.replyGreetingWith(greetingRequest);
            response = Response.ok(gson.toJson(reply)).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            response = Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return response;

    }


}
