package com.lenora.staj.websocket.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    @Value("${jwt.token.secret}")
    public String jwtSecret;

    public String createJWT(String userName) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("lenora")
                .withClaim("userName", userName)
                .sign(algorithm);
    }

    public boolean validate(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("lenora")
                    // reusable verifier instance
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String tokenUser = decodedJWT.getClaim("userName").toString();
            return username.contentEquals(tokenUser);
        }
        catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        JWTVerifier verifier = JWT.require(algorithm)
                // specify any specific claim validations
                .withIssuer("lenora")
                // reusable verifier instance
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("userName").asString();
    }
}
