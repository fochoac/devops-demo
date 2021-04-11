package ec.greeting.util;

import java.util.ResourceBundle;

public final class ResourceBundleUtil {
    private static final ResourceBundleUtil INSTANCE = new ResourceBundleUtil();
    private final ResourceBundle confBundle;
    private final ResourceBundle messageBundle;

    private ResourceBundleUtil() {
        confBundle = ResourceBundle.getBundle("ec.greeting.properties.ConfigurationBundle");
        messageBundle = ResourceBundle.getBundle("i18n.MessageBundle");
    }

    public static ResourceBundleUtil getInstance() {
        return INSTANCE;
    }

    public String getConfiguration(String confId) {
        return this.confBundle.getString(confId);
    }

    public String getMessage(String messageId) {
        return this.messageBundle.getString(messageId);
    }


}
