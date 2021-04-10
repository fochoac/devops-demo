package ec.greeting.service;

import ec.greeting.model.ws.Greeting;

import javax.enterprise.context.RequestScoped;
import java.util.Objects;

@RequestScoped
public class GreetingService {
    private static final String GREETING_PATTERN = "Hello %s your message will be send";

    public Greeting replyGreetingWith(final Greeting greetingRequest) {
        validateRequieredFieldsFrom(greetingRequest);
        Greeting greetingResponse = new Greeting();
        greetingResponse.setMessage(String.format(GREETING_PATTERN, greetingRequest.getTo()));
        return greetingResponse;
    }

    private void validateRequieredFieldsFrom(Greeting greetingRequest) {
        Objects.requireNonNull(greetingRequest, "The greeter class should not null");
        Objects.requireNonNull(greetingRequest.getTo(), "The greeter to parameter should not null");
    }
}
