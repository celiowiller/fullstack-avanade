package com.agro.sensores.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Classe de configuração de segurança
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter filter;

    // Define regras de segurança
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF (API stateless)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sem sessão
                .authorizeHttpRequests(auth -> auth

                        // Endpoint público de login
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // Swagger liberado
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Apenas ADMIN acessa usuários
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        // Demais precisam estar autenticados
                        .anyRequest().authenticated()
                )
                // Adiciona filtro JWT
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Gerenciador de autenticação
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Encoder de senha
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}