package com.whatsapi.restful.util;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import java.time.Instant;

import javax.crypto.SecretKey;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private SecretKey jwtSecretKey = getSigningKey();
    private Base64.Decoder decoder = Base64.getUrlDecoder();

    SecretKey getSigningKey() {
        SecretKey temp = Jwts.SIG.HS512.key().build();
        return temp;
    }

    public String extractEmail(String token) {
        // System.out.println(Base64.getEncoder().encodeToString(jwtSecretKey.getEncoded()));
        String[] chunks = token.split("\\.");
        JSONObject json = new JSONObject(new String(decoder.decode(chunks[1])));
        return json.getString("sub");
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String subject) {
        // System.out.println(Base64.getEncoder().encodeToString(jwtSecretKey.getEncoded()));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 300 * 1000)) // 30 seconds
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(String token) throws JwtException {
        String[] chunks = token.split("\\.");
        JSONObject json = new JSONObject(new String(decoder.decode(chunks[1])));
        System.out.println(json.getInt("exp"));
        System.out.println(Instant.now().getEpochSecond());
        return true;
    }
}