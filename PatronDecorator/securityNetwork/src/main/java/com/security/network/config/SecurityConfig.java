package com.security.network.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())  // Habilita CORS con configuración por defecto
            .csrf(csrf -> csrf.disable())     // Desactiva CSRF (recomendado para APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/api/security/**").permitAll() // Acceso libre
                .anyRequest().authenticated() // Protege otros endpoints si los hubiera
            );

        return http.build();
    }
}
