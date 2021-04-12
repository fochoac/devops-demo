package ec.greeting.model.ws;

import lombok.Getter;
import lombok.Setter;

public class JwtToken {
    @Getter
    @Setter
    private String token;

    public JwtToken() {
        super();
    }
}
