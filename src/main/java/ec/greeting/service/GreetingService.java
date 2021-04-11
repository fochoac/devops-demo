package ec.greeting.service;

import ec.greeting.model.ws.Greeting;
import ec.greeting.util.ResourceBundleUtil;

import javax.enterprise.context.RequestScoped;
import java.util.Objects;

@RequestScoped
public class GreetingService {


    private static final String GREETING_MESSAGE_PATTERN = ResourceBundleUtil.getInstance().getMessage("greeting.message.pattern");
    private static final String GREETING_MESSAGE_ERROR_REQUIERED_NOTNULL = ResourceBundleUtil.getInstance().getMessage("greeting.message.error.requiered.notnull");
    private static final String GREETING_MESSAGE_ERROR_REQUIERED_PARAMETER_NOTNULL = ResourceBundleUtil.getInstance().getMessage("greeting.message.error.requiered.parameter.notnull");

    public Greeting replyGreetingWith(final Greeting greetingRequest) {
        validateRequieredFieldsFrom(greetingRequest);
        Greeting greetingResponse = new Greeting();
        greetingResponse.setMessage(String.format(GREETING_MESSAGE_PATTERN, greetingRequest.getTo()));
        return greetingResponse;
    }

    private void validateRequieredFieldsFrom(Greeting greetingRequest) {
        Objects.requireNonNull(greetingRequest, GREETING_MESSAGE_ERROR_REQUIERED_NOTNULL);
        Objects.requireNonNull(greetingRequest.getTo(), String.format(GREETING_MESSAGE_ERROR_REQUIERED_PARAMETER_NOTNULL, "TO"));
    }
}
