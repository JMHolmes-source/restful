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
        model.addAttribute("message","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xtZXNqb3NodWEwMUBnbWFpbC5jb20iLCJpYXQiOjE3NDIyNDkyMjYsImV4cCI6MTc0MjI0OTI1Nn0.AMijX3MPe6VAHhycibt8kPxlNjJiUxJygdn8F1EjM84");
        return "index";
    }
}
