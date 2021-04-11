package ec.greeting.configuration;


import ec.greeting.api.filter.ApiKeyFilter;
import ec.greeting.api.filter.TypeRequestFilter;
import ec.greeting.api.ws.EchoWs;
import ec.greeting.api.ws.GreetingWs;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@OpenAPIDefinition(
        info = @Info(title = "DevOpsApi", version = "v1.0.0", contact = @Contact(), license = @License(name = "APACHE")),
        servers = @Server(url = "http://localhost:8084/v1")
)
@SecuritySchemes({
        @SecurityScheme(securitySchemeName = "X-Parse-REST-API-Key", apiKeyName = "X-Parse-REST-API-Key", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER),
        @SecurityScheme(securitySchemeName = "X-JWT-KWY", apiKeyName = "X-JWT-KWY", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
})
@ApplicationPath("v1")
public class AppBaseConfiguration extends Application {
    private final Set<Class<?>> classes = new HashSet<>();

    public AppBaseConfiguration() {
        initializeApplication();

    }

    private void initializeApplication() {
        registerProviders();
        registerClasses();
    }

    @Override
    public Set<Class<?>> getClasses() {
        return this.classes;
    }

    private void registerClasses() {
        classes.add(EchoWs.class);
        classes.add(GreetingWs.class);
    }


    private void registerProviders() {
        classes.add(TypeRequestFilter.class);
        classes.add(ApiKeyFilter.class);

    }
}
