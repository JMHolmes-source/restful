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
        // Arrange
        String token = jwtUtil.generateToken(TEST_EMAIL);

        // Act
        String extractedEmail = jwtUtil.extractEmail(token);

        // Assert
        assertEquals(TEST_EMAIL, extractedEmail);
    }

    @Test
    void extractEmail_ShouldHandleInvalidTokenFormat() {
        // Arrange
        String invalidToken = "invalid.token";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> jwtUtil.extractEmail(invalidToken));
    }

    @Test
    void extractEmail_ShouldHandleInvalidBase64Encoding() {
        // Arrange
        String invalidToken = "header.%%%invalid%%%payload.signature";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> jwtUtil.extractEmail(invalidToken));
    }

    @Test
    void extractEmail_ShouldHandleMissingSubClaim() {
        // Arrange
        String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS512\"}".getBytes());
        String payload = Base64.getUrlEncoder().encodeToString("{\"iat\":1616239022}".getBytes());
        String invalidToken = header + "." + payload + ".signature";

        // Act & Assert
        assertThrows(Exception.class, () -> jwtUtil.extractEmail(invalidToken));
    }

    @Test
    void generateToken_ShouldCreateValidToken() {
        // Act
        String token = jwtUtil.generateToken(TEST_EMAIL);

        // Assert
        assertNotNull(token);
        assertTrue(token.contains("."));
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length);

        // Verify the token contains the correct email
        String extractedEmail = jwtUtil.extractEmail(token);
        assertEquals(TEST_EMAIL, extractedEmail);
    }

    @Test
    void generateToken_ShouldCreateTokenWithExpiration() {
        // Act
        String token = jwtUtil.generateToken(TEST_EMAIL);
        String[] chunks = token.split("\\.");
        JSONObject payload = new JSONObject(new String(Base64.getUrlDecoder().decode(chunks[1])));

        // Assert
        assertTrue(payload.has("exp"));
        long expirationTime = payload.getLong("exp");
        long currentTime = System.currentTimeMillis() / 1000;

        // Token should expire in approximately 5 minutes (300 seconds)
        long expectedExpirationTime = currentTime + 300;
        long tolerance = 10; // Allow for small timing differences

        assertTrue(Math.abs(expirationTime - expectedExpirationTime) <= tolerance);
    }

    @Test
    void createToken_ShouldIncludeAllRequiredClaims() {
        // Act
        Map<String, Object> claims = new HashMap<>();
        claims.put("test-claim", "test-value");

        // Using reflection to access private method for testing
        String token = jwtUtil.generateToken(TEST_EMAIL); // Using generateToken as a wrapper
        String[] chunks = token.split("\\.");
        JSONObject payload = new JSONObject(new String(Base64.getUrlDecoder().decode(chunks[1])));

        // Assert
        assertTrue(payload.has("sub"));
        assertTrue(payload.has("iat"));
        assertTrue(payload.has("exp"));
        assertEquals(TEST_EMAIL, payload.getString("sub"));
    }

    @Test
    void createToken_ShouldUseCorrectSigningAlgorithm() {
        // Act
        String token = jwtUtil.generateToken(TEST_EMAIL);
        String[] chunks = token.split("\\.");
        JSONObject header = new JSONObject(new String(Base64.getUrlDecoder().decode(chunks[0])));

        // Assert
        assertEquals("HS512", header.getString("alg"));
    }
}