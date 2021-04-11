package ec.greeting.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceBundleUtilTest {
    private final ResourceBundleUtil resourceBundleUtil = ResourceBundleUtil.getInstance();

    @Test
    public void getConfigurationRandomAlphabet() {
        final String jwtRandomAlphabet = resourceBundleUtil.getConfiguration("jwt.random.alphabet");
        assertEquals("123456789AbcdefghijklmnopqrstuvwxyzBCEFGHIJKLMNOPQUSTUVWXYZ", jwtRandomAlphabet);
    }

    @Test
    public void getMessageErrorToken() {
        final String messageErrorToken = resourceBundleUtil.getMessage("ws.filter.message.not.api.key");
        assertEquals("Please provide a valid API Key", messageErrorToken);
    }
}