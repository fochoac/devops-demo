package ec.greeting.common;


import com.google.gson.Gson;
import ec.greeting.model.ws.Greeting;

public interface CommonTest {
    String EXPECTED_GREETING_MESSAGE = "Hello Juan Perez your message will be send";

    default Greeting getGreetingTemplate() {
        Gson gson = new Gson();
        return gson.fromJson("{\n" +
                "  \"message\": \"This is a test\",\n" +
                "  \"to\": \"Juan Perez\",\n" +
                "  \"from\": \"Rita Asturia\",\n" +
                "  \"timeToLifeSec\": 45\n" +
                "}", Greeting.class);
    }
}
