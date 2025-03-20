package com.whatsapi.restful.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.json.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    private final String TEST_EMAIL = "test@example.com";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void extractEmail_ShouldReturnCorrectEmail() {
        String token = jwtUtil.generateToken(TEST_EMAIL);
        String extractedEmail = jwtUtil.extractEmail(token);
        assertEquals(TEST_EMAIL, extractedEmail);
    }

    @Test
    void extractEmail_ShouldHandleInvalidTokenFormat() {
        String invalidToken = "invalid.token";

        assertThrows(IllegalArgumentException.class, () -> jwtUtil.extractEmail(invalidToken));
    }

    @Test
    void extractEmail_ShouldHandleInvalidBase64Encoding() {
        String invalidToken = "header.%%%invalid%%%payload.signature";

        assertThrows(IllegalArgumentException.class, () -> jwtUtil.extractEmail(invalidToken));
    }

    @Test
    void extractEmail_ShouldHandleMissingSubClaim() {
        String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS512\"}".getBytes());
        String payload = Base64.getUrlEncoder().encodeToString("{\"iat\":1616239022}".getBytes());
        String invalidToken = header + "." + payload + ".signature";

        assertThrows(Exception.class, () -> jwtUtil.extractEmail(invalidToken));
    }

    @Test
    void generateToken_ShouldCreateValidToken() {
        String token = jwtUtil.generateToken(TEST_EMAIL);

        assertNotNull(token);
        assertTrue(token.contains("."));
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length);

        String extractedEmail = jwtUtil.extractEmail(token);
        assertEquals(TEST_EMAIL, extractedEmail);
    }

    @Test
    void generateToken_ShouldCreateTokenWithExpiration() {
        String token = jwtUtil.generateToken(TEST_EMAIL);
        String[] chunks = token.split("\\.");
        JSONObject payload = new JSONObject(new String(Base64.getUrlDecoder().decode(chunks[1])));

        assertTrue(payload.has("exp"));
        long expirationTime = payload.getLong("exp");
        long currentTime = System.currentTimeMillis() / 1000;

        long expectedExpirationTime = currentTime + 300;
        long tolerance = 10; // Allow for small timing differences

        assertTrue(Math.abs(expirationTime - expectedExpirationTime) <= tolerance);
    }

    @Test
    void createToken_ShouldIncludeAllRequiredClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("test-claim", "test-value");

        String token = jwtUtil.generateToken(TEST_EMAIL); // Using generateToken as a wrapper
        String[] chunks = token.split("\\.");
        JSONObject payload = new JSONObject(new String(Base64.getUrlDecoder().decode(chunks[1])));

        assertTrue(payload.has("sub"));
        assertTrue(payload.has("iat"));
        assertTrue(payload.has("exp"));
        assertEquals(TEST_EMAIL, payload.getString("sub"));
    }

    @Test
    void createToken_ShouldUseCorrectSigningAlgorithm() {
        String token = jwtUtil.generateToken(TEST_EMAIL);
        String[] chunks = token.split("\\.");
        JSONObject header = new JSONObject(new String(Base64.getUrlDecoder().decode(chunks[0])));

        assertEquals("HS512", header.getString("alg"));
    }
}