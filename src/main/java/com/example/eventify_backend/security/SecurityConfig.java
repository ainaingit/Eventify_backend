package com.example.eventify_backend.security;

import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;

    // Injection du service UserService et du filtre JwtRequestFilter
    public SecurityConfig(UserService userService, JwtRequestFilter jwtRequestFilter) {
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactiver CSRF pour les API REST
                .authorizeRequests()
                // Autorise toutes les requêtes OPTIONS (préflight)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Autorise les endpoints publics
                .requestMatchers("/error", "/api/auth/login", "/api/auth/register").permitAll()
                // Toutes les autres requêtes nécessitent une authentification
                .anyRequest().authenticated()
                .and()
                // Ajouter le filtre JWT avant le filtre de gestion d'authentification classique
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return numero -> {
            UserEntity appUser = userService.getUserByNumber(numero);
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
