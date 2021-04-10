package ec.greeting.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class SecurityService {
    public static final String ALPHABET_RANDOM = "123456789AbcdefghijklmnopqrstuvwxyzBCEFGHIJKLMNOPQUSTUVWXYZ";
    private static final String AUTH_0 = "auth0";
    private static final String USER = "user";
    private static final int NUMBER_SECONDS_IS_VALID_REQUEST = 30;
    private static final String SECRET_TEST_CODE_NOT_PRODUCTION = "testjcasw2021";
    private static final Algorithm ALGORITHM_HMAC256 = Algorithm.HMAC256(SECRET_TEST_CODE_NOT_PRODUCTION);

    @Inject
    private Logger logger;

    public String generateJWTToken() {
        final String userCode = UUID.randomUUID().toString();
        String[] claims;
        claims = new String[1];
        claims[0] = userCode;
        LocalDateTime localDateTime = LocalDateTime.now();
        return JWT.create()
                .withIssuer(AUTH_0)
                .withClaim(USER, userCode)
                .withExpiresAt(Date.from(localDateTime.plusSeconds(NUMBER_SECONDS_IS_VALID_REQUEST).atZone(ZoneId.systemDefault()).toInstant()))
                .withIssuedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withSubject(generateRandomSubject())
                .sign(ALGORITHM_HMAC256);


    }

    public boolean isValidJWTToken(String jwtString) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(SECRET_TEST_CODE_NOT_PRODUCTION);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(AUTH_0).build();
            verifier.verify(jwtString);

            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    private String generateRandomSubject() {
        Random r = new Random();

        List<String> alphabet = Arrays.asList(ALPHABET_RANDOM.split("(?!^)"));

        Collections.shuffle(alphabet);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(alphabet.get(r.nextInt(alphabet.size())));
        }
        return builder.toString();

    }


}
