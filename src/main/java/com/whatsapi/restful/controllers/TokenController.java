package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @GetMapping("/api/token")
    public String getToken(Authentication authentication) {
        System.out.println("\n\n\n");
        System.out.println(authentication.getPrincipal());
        System.out.println("\n\n\n");
        return authentication.getPrincipal().toString();
    }
}
