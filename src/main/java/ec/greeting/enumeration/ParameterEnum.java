package ec.greeting.enumeration;

import ec.greeting.util.ResourceBundleUtil;
import lombok.Getter;

public enum ParameterEnum {
    JWT_RANDOM_ALPHABET(ResourceBundleUtil.getInstance().getConfiguration("jwt.random.alphabet")),
    JWT_ISSUER_CODE(ResourceBundleUtil.getInstance().getConfiguration("jwt.issuer.code")),
    JWT_DEFAULT_USER(ResourceBundleUtil.getInstance().getConfiguration("jwt.default.user")),
    JWT_TIME_TO_LIFE_SECONDS(ResourceBundleUtil.getInstance().getConfiguration("jwt.time.to.life.seconds")),
    JWT_DEFAULT_SECRET(ResourceBundleUtil.getInstance().getConfiguration("jwt.secret")),
    // API KEYS
    API_KEY_PARSE_REST(ResourceBundleUtil.getInstance().getConfiguration("ws.api.key.x.parse.rest")),
    API_KEY_PARSE_REST_DEFAUTL_VALUE(ResourceBundleUtil.getInstance().getConfiguration("ws.api.key.x.parse.rest.default.value")),
    API_KEY_JWT_KWY(ResourceBundleUtil.getInstance().getConfiguration("ws.api.key.x.jwt.kwy")),
    ;
    @Getter
    private final String value;

    ParameterEnum(String value) {
        this.value = value;
    }
}
