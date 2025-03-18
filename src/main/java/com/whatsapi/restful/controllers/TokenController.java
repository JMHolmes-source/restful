package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;

import java.net.http.HttpResponse;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RestController
public class TokenController {

    @GetMapping("/token")
    public String index(OAuth2AuthenticationToken authentication, Model model) {
        System.out.println(authentication.getPrincipal().getAttributes().get("email"));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xtZXNqb3NodWEwMUBnbWFpbC5jb20iLCJpYXQiOjE3NDIyNDkyMjYsImV4cCI6MTc0MjI0OTI1Nn0.AMijX3MPe6VAHhycibt8kPxlNjJiUxJygdn8F1EjM84";
        // model.addAttribute("message","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xtZXNqb3NodWEwMUBnbWFpbC5jb20iLCJpYXQiOjE3NDIyNDkyMjYsImV4cCI6MTc0MjI0OTI1Nn0.AMijX3MPe6VAHhycibt8kPxlNjJiUxJygdn8F1EjM84");
        return "<button onclick=\"window.location.href='http://localhost:6969/callback?code="+token+"'\">Go to Callback</button>";
    }
}
