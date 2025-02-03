package com.example.eventify_backend.security;

import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Désactive la protection CSRF
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll() // Permet l'accès sans authentification
                                .anyRequest().authenticated() // Authentifie toutes les autres requêtes
                )
                ; // Utilise HTTP Basic pour l'authentification basique

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity appUser = userService.getUserByUsername(username);
            return org.springframework.security.core.userdetails.User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
