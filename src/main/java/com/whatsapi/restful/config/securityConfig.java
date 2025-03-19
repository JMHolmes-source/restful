package com.whatsapi.restful.config;

import org.springframework.context.annotation.*;
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
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/").authenticated();
            auth.anyRequest().permitAll();
            // auth.requestMatchers("/token").authenticated();
        });
        http.oauth2Login(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
}