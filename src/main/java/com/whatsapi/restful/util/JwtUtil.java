package com.whatsapi.restful.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private SecretKey jwtSecretKey = getSigningKey();

    SecretKey getSigningKey() {
        return Jwts.SIG.HS512.key().build();
    }

    // public Key getSecretKey() {
    // return SECRET_KEY;
    // }

    // public String extractUsername(String token) throws ExpiredJwtException {
    // return extractClaim(token, Claims::getSubject);
    // }

    // // New method to extract username even from expired tokens
    // public String extractUsernameFromExpiredToken(String token) {
    // try {
    // return extractUsername(token);
    // } catch (ExpiredJwtException e) {
    // // If token is expired, get username from the JWT claims
    // return e.getClaims().getSubject();
    // }
    // }

    // public Date extractExpiration(String token) throws ExpiredJwtException {
    // return extractClaim(token, Claims::getExpiration);
    // }

    // public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    // throws ExpiredJwtException {
    // final Claims claims = extractAllClaims(token);
    // return claimsResolver.apply(claims);
    // }

    // private Claims extractAllClaims(String token) throws ExpiredJwtException {
    // return
    // Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    // }

    // private Boolean isTokenExpired(String token) throws ExpiredJwtException {
    // return extractExpiration(token).before(new Date());
    // }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String subject) {
        System.out.println(Base64.getEncoder().encodeToString(jwtSecretKey.getEncoded()));
        return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 300 * 1000)) // 30 seconds
        .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
        .compact();
    }

    // public Boolean validateToken(String token, String username) throws
    // ExpiredJwtException {
    // final String extractedUsername = extractUsername(token);
    // return (extractedUsername.equals(username) && !isTokenExpired(token));
    // }
}