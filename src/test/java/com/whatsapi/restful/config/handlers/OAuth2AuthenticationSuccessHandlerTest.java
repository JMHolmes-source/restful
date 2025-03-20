package com.whatsapi.restful.config.handlers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
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
                "google"
        );

        // Setup for capturing System.out.println output
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    public void testOnAuthenticationSuccess_WithOAuth2Token() throws IOException, ServletException {
        // Execute the method
        handler.onAuthenticationSuccess(request, response, filterChain, oAuth2AuthenticationToken);

        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void testOnAuthenticationSuccess_WithNonOAuth2Token() throws IOException, ServletException {
        handler.onAuthenticationSuccess(request, response, filterChain, nonOAuth2Authentication);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testOnAuthenticationSuccess_ValidatesEmail() throws IOException, ServletException {
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

        handler.onAuthenticationSuccess(request, response, filterChain, differentEmailToken);
        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void testOnAuthenticationSuccess_MissingEmailAttribute() throws IOException, ServletException {
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

        handler.onAuthenticationSuccess(request, response, filterChain, noEmailToken);

        verifyNoMoreInteractions(filterChain);
    }

    @Test
    public void testOnAuthenticationSuccess_WithDifferentOAuthProvider() throws IOException, ServletException {
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
                "facebook"
        );

        handler.onAuthenticationSuccess(request, response, filterChain, facebookToken);

        verifyNoMoreInteractions(filterChain);
    }
}