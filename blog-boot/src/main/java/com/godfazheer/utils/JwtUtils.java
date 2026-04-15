package com.godfazheer.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    public static final String tokenHead = "fazheer-token";
    private static final long EXPIRATION_TIME = 60 * 60 * 24 * 1000;

    @Value("${jwt.secret:}")
    private String secret;
    private SecretKey secretKey;
    private static final String DEFAULT_USERNAME = "joker";

    @PostConstruct
    public void init() {
        if (secret == null || secret.isBlank() || secret.length() < 32) {
            log.warn("JWT secret 长度不足 32 字节，请配置足够强度的 jwt.secret");
            // 兜底但会打 warn，生产环境必须配置
            this.secret = "godfazheer_secret_default_key_32b";
        }
        this.secretKey = Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(Map<String, Object> payloads) {
        return Jwts.builder()
                .signWith(secretKey)
                .claims(payloads)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .signWith(secretKey)
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    public Claims parseJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
