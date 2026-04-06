package com.agro.sensores.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

// Classe de configuração de beans globais reutilizáveis
@Configuration
public class BeansConfig {

    // Bean de Clock (permite controlar tempo em testes)
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}