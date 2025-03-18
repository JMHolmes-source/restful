package com.whatsapi.restful.util;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    // private SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();

    // public JwtUtil() {
    //     SecretKey tempKey;
    //     try {
    //         tempKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //     } catch (Exception e) {
    //         throw new IllegalStateException("Failed to create JWT Secret Key", e);
    //     }
    //     this.SECRET_KEY = tempKey;
    // }

    // public Key getSecretKey() {
    //     return SECRET_KEY;
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

    // public String generateToken(String username) {
    // Map<String, Object> claims = new HashMap<>();
    // return createToken(claims, username);
    // }

    // private String createToken(Map<String, Object> claims, String subject) {
    // return Jwts.builder()
    // .setClaims(claims)
    // .setSubject(subject)
    // .setIssuedAt(new Date(System.currentTimeMillis()))
    // .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000)) // 30
    // seconds
    // .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
    // .compact();
    // }

    // public Boolean validateToken(String token, String username) throws
    // ExpiredJwtException {
    // final String extractedUsername = extractUsername(token);
    // return (extractedUsername.equals(username) && !isTokenExpired(token));
    // }
}