package com.godfazheer.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    public static final String tokenHead = "fazheer-token";
    private static final long EXPIRATION_TIME =60*60*24*1000;
    private static final String SECRET  = "godfazheer_secret_fuck_u";
    private static final String DEFAULT_USERNAME = "joker";
    public static String generateJwtToken(Map<String,Object> payloads) {

       return  Jwts.builder()
               .signWith(SignatureAlgorithm.HS256,SECRET)
               .addClaims(payloads)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
               .compact();
    }
    public static String generateJwtToken(String username) {

       return  Jwts.builder()
               .signWith(SignatureAlgorithm.HS256,SECRET)
               .claim("username",username)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
               .compact();
    }
    public static Claims parseJwtToken(String token) {
        return (Claims) Jwts.parser().setSigningKey(SECRET).parse(token).getBody();
    }

}


