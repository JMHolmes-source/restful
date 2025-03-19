package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.*;

import com.whatsapi.restful.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;

@RestController
public class TokenController {
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
    public String index(OAuth2AuthenticationToken authentication, Model model) {
        System.out.println(authentication.getPrincipal().getAttributes().get("email"));
        String token = jwtUtil.generateToken((String) authentication.getPrincipal().getAttributes().get("email"));
        String page = String.format("""
                <!DOCTYPE html>
                <html xmlns:th="http://www.thymeleaf.org">
                <head>
                    <meta charset="UTF-8">
                    <title>Greeting Page</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            text-align: center;
                            background-color:#333;
                        }
                        .container {
                            margin-top: 100px;
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            gap: 20px;
                        }
                        .button {
                            padding: 10px 20px;
                            background-color: #007bff;
                            color: #fff;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            font-size: 16px;
                            transition: background-color 0.3s;
                        }
                        .button:hover {
                            background-color: #009bff;
                        }

                        .buttonLogout {
                            background-color: #333;
                            color: #eee;
                        }

                        .buttonLogout:hover {
                            background-color: #444;
                        }
                    </style>
                </head>
                <body>
                <div class="container">
                    <button class="button" onclick=\"window.location.href='http://localhost:6969/callback?code=%1$s'\">Login as %2$s</button>
                    <button class="button buttonLogout" onclick=\"window.location.href='/logout'\">Login as a different user</button>
                </div>
                </body>
                </html>
                """,
                token, (String) authentication.getPrincipal().getAttributes().get("email"));
        return page;
    }
}
