package com.whatsapi.restful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    // @Bean
    // @Order(1)
    // SecurityFilterChain

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").authenticated();
                    auth.anyRequest().permitAll();
                    // auth.requestMatchers("/token").authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .csrf()
                .disable()
                .build();
    }
}