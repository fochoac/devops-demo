package ec.greeting.model.ws;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Greeting")
public class Greeting implements Serializable {
    @Getter
    @Setter
    @XmlElement(name = "message")
    private String message;
    @Getter
    @Setter
    @XmlElement(name = "to")
    private String to;
    @Getter
    @Setter
    @XmlElement(name = "from")
    private String from;
    @Getter
    @Setter
    @XmlElement(name = "timeToLifeSec")
    private Long timeToLifeSec;

    public Greeting() {
        super();
    }
}
