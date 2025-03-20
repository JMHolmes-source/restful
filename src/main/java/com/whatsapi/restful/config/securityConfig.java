package com.whatsapi.restful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.whatsapi.restful.config.handlers.JWTfilter;

@Configuration
@EnableWebSecurity
public class securityConfig {

    // @Bean
    // @Order(1)
    // SecurityFilterChain

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSecurity temp = http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/").authenticated();
            auth.anyRequest().permitAll();
            // auth.requestMatchers("/token").authenticated();
        });
        temp.oauth2Login(Customizer.withDefaults());
        temp.csrf().disable();
        temp.addFilterBefore(JWTfilter(), UsernamePasswordAuthenticationFilter.class);
        return temp.build();
    }

    @Bean
    public JWTfilter JWTfilter() {
        return new JWTfilter();
    }
}