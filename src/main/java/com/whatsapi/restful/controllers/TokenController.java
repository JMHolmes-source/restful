package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TokenController {

    @GetMapping("/token")
    public String index(OAuth2AuthenticationToken authentication, Model model) {
        System.out.println(authentication.getPrincipal().getAttributes().get("email"));
        return "index.html";
    }
}
