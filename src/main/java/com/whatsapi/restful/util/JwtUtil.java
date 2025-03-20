package com.whatsapi.restful.util;

import java.sql.Date;
import java.util.*;
import javax.crypto.SecretKey;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
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

    // private Claims getClaims(String token) {
        
    //     // return null;
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
    // Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody();
    // }

    // // private Boolean isTokenExpired(String token) throws ExpiredJwtException {
    // // return extractExpiration(token).before(new Date());
    // // }

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

    // public Boolean validateToken(String token, String username) throws
    // ExpiredJwtException {
    // final String extractedUsername = extractUsername(token);
    // return (extractedUsername.equals(username) && !isTokenExpired(token));
    // }
}