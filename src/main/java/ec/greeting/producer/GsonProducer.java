package ec.greeting.producer;

import com.google.gson.Gson;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
public class GsonProducer {
    @Produces
    public Gson getLogger(InjectionPoint p) {
        return new Gson();
    }
}
