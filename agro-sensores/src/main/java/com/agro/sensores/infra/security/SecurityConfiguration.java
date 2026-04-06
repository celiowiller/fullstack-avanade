package com.agro.sensores.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity // <--- ADICIONANDO NOVA ANNOTATION PARA QUE TUDO 
//OCORRA EM MODO "SEGURO"
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter filter;
       
    // Define regras de segurança
    // dentro de uma classe anotada com @Configuration, não é necessário que 
    // os métodos @Bean sejam public
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Mantém desativado para API
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Liberação do Swagger (Adicionei o /swagger-ui.html e /swagger-ui/index.html)
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 2. Liberação do H2 Console (Obrigatório para ambiente dev)
                        .requestMatchers("/h2-console/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers("/usuarios/**").permitAll()
                        .anyRequest().authenticated()
                )
                // 3. Importante: O H2 usa frames. O Spring bloqueia por padrão. 
                // Esta linha permite que o H2 abra no navegador:
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

   
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
       
    @Bean
    AuthenticationProvider authenticationProvider(AutenticacaoService autenticacaoService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(autenticacaoService); // Use o objeto que você criou
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    // Encoder de senha
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



