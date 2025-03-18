package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whatsapi.restful.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;

@RestController
public class TokenController {
@Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/token")
    public String index(OAuth2AuthenticationToken authentication, Model model) {
        System.out.println(authentication.getPrincipal().getAttributes().get("email"));
        String token = jwtUtil.generateToken((String)authentication.getPrincipal().getAttributes().get("email"));
        return "<button onclick=\"window.location.href='http://localhost:6969/callback?code="+token+"'\">Go to Callback</button>";
    }
}
