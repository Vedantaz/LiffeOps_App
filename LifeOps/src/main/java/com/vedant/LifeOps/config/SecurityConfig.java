package com.vedant.LifeOps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        System.out.println(">>> Custom SecurityConfig Loaded <<<");

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth ->
                auth
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // Public read API's
                .requestMatchers(HttpMethod.GET, "/tasks/**").permitAll()

                // Secure write APIs
                .requestMatchers(HttpMethod.POST, "/tasks/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/tasks/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/tasks/**").authenticated()
                .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }
}
