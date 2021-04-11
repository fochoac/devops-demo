package ec.greeting.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import ec.greeting.enumeration.ParameterEnum;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class SecurityService {
    private static final Algorithm ALGORITHM_HMAC256 = Algorithm.HMAC256(ParameterEnum.JWT_DEFAULT_SECRET.getValue());

    @Inject
    private Logger logger;

    public String generateJWTToken() {
        final String userCode = UUID.randomUUID().toString();
        String[] claims;
        claims = new String[1];
        claims[0] = userCode;
        LocalDateTime localDateTime = LocalDateTime.now();
        Date expirationTime = Date.from(localDateTime
                .plusSeconds(Long.valueOf(ParameterEnum.JWT_TIME_TO_LIFE_SECONDS.getValue()))
                .atZone(ZoneId.systemDefault()).toInstant());
        Date issuerDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create()
                .withIssuer(ParameterEnum.JWT_ISSUER_CODE.getValue())
                .withClaim(ParameterEnum.JWT_DEFAULT_USER.getValue(), userCode)
                .withExpiresAt(expirationTime)
                .withIssuedAt(issuerDate)
                .withSubject(generateRandomSubject())
                .sign(ALGORITHM_HMAC256);


    }

    public boolean isValidJWTToken(String jwtString) {
        try {

            JWTVerifier verifier = JWT
                    .require(ALGORITHM_HMAC256)
                    .withIssuer(ParameterEnum.JWT_ISSUER_CODE.getValue())
                    .build();
            verifier.verify(jwtString);

            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    public boolean isValidRestApiKey(String restApiKey) {
        return Optional.ofNullable(restApiKey)
                .filter(t -> Objects.nonNull(t)
                        && !t.trim().isEmpty()
                        && t.equals(ParameterEnum.API_KEY_PARSE_REST_DEFAUTL_VALUE.getValue()))
                .isPresent();
    }

    private String generateRandomSubject() {
        List<String> alphabet = Arrays.asList(ParameterEnum.JWT_RANDOM_ALPHABET.getValue().split("(?!^)"));
        Collections.shuffle(alphabet);
        return alphabet.subList(1, 10).stream().reduce("", String::concat);
    }


}
