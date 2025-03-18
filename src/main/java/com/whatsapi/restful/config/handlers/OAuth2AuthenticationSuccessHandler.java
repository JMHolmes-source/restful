package com.whatsapi.restful.config.handlers;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
            System.out.println("\n\n\n");
            System.out.println(request);
            System.out.println("\n\n\n");
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            System.out.println(""+oAuth2AuthenticationToken.getPrincipal().getAttribute("email"));
            response.sendRedirect("/token");
        } else {
            super.onAuthenticationSuccess(request, response, chain, authentication);
        }
    }
}
