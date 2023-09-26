package com.leo.demo.shopping.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leo.demo.shopping.models.entities.User;

import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userName", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("user").asMap().get("userName").toString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static User getUser(String token) {
        User user = new User();
        try {
            DecodedJWT jwt = JWT.decode(token);
            var map = jwt.getClaim("user").asMap();
            user.setName(map.get("userName").toString());
            user.setMobile(map.get("mobile").toString());
            return user;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String sign(Map<String, String> map, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("user", map)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
