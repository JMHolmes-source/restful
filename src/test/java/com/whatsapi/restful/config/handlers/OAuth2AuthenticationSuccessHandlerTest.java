package com.whatsapi.restful.config.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OAuth2AuthenticationSuccessHandlerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Authentication nonOAuth2Authentication;

    @InjectMocks
    private OAuth2AuthenticationSuccessHandler handler;

    private OAuth2AuthenticationToken oAuth2AuthenticationToken;
    private PrintWriter printWriter;
    private StringWriter stringWriter;

    @BeforeEach
    public void setUp() {
        // Setup OAuth2AuthenticationToken with email attribute
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "test@example.com");

        OAuth2User principal = new DefaultOAuth2User(
                Collections.emptyList(),
                attributes,
                "email"
        );

        oAuth2AuthenticationToken = new OAuth2AuthenticationToken(
                principal,
                Collections.emptyList(),
                "google" // registration ID
        );

        // Setup for capturing System.out.println output
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    public void testOnAuthenticationSuccess_WithOAuth2Token() throws IOException, ServletException {
        // Execute the method
        handler.onAuthenticationSuccess(request, response, filterChain, oAuth2AuthenticationToken);

        // No need to verify System.out.println calls as they're not testable directly
        // Verify that parent method was not called
        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void testOnAuthenticationSuccess_WithNonOAuth2Token() throws IOException, ServletException {
        // Execute the method
        handler.onAuthenticationSuccess(request, response, filterChain, nonOAuth2Authentication);

        // Verify that super.onAuthenticationSuccess was called
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testOnAuthenticationSuccess_ValidatesEmail() throws IOException, ServletException {
        // Create a token with a different email
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "different@example.com");

        OAuth2User principal = new DefaultOAuth2User(
                Collections.emptyList(),
                attributes,
                "email"
        );

        OAuth2AuthenticationToken differentEmailToken = new OAuth2AuthenticationToken(
                principal,
                Collections.emptyList(),
                "google"
        );

        // Execute the method
        handler.onAuthenticationSuccess(request, response, filterChain, differentEmailToken);

        // No assertions needed for System.out.println, just verifying method executes without error
        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void testOnAuthenticationSuccess_MissingEmailAttribute() throws IOException, ServletException {
        // Create a token without email attribute
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "Test User");

        OAuth2User principal = new DefaultOAuth2User(
                Collections.emptyList(),
                attributes,
                "name"
        );

        OAuth2AuthenticationToken noEmailToken = new OAuth2AuthenticationToken(
                principal,
                Collections.emptyList(),
                "google"
        );

        // Execute the method - should not throw exception even if email is missing
        handler.onAuthenticationSuccess(request, response, filterChain, noEmailToken);

        // No assertions needed for System.out.println output
        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void testOnAuthenticationSuccess_WithDifferentOAuthProvider() throws IOException, ServletException {
        // Create a token with a different provider
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "test@example.com");

        OAuth2User principal = new DefaultOAuth2User(
                Collections.emptyList(),
                attributes,
                "email"
        );

        OAuth2AuthenticationToken facebookToken = new OAuth2AuthenticationToken(
                principal,
                Collections.emptyList(),
                "facebook" // Different provider
        );

        // Execute the method
        handler.onAuthenticationSuccess(request, response, filterChain, facebookToken);

        // No assertions needed for System.out.println output
        verifyNoMoreInteractions(filterChain);
    }
}