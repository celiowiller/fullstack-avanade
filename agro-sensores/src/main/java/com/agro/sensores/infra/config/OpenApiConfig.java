package com.agro.sensores.infra.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

// Classe de configuração do Swagger / OpenAPI
// ou seja, é esta estrutura de codigo que permite ao Swagger UI - Interface do swagger
// "conversar" com o noo SecurityFilter.
@Configuration
public class OpenApiConfig {

    // Bean principal que configura documentação da API
    // @Bean é um dos "pilares" do contexto de injeção de dependencia!
    /*
    * quando anotamos qualquer instrução como @Bean estamos dizendo que: o Spring execute este
    * // método apenas uma vez(padrão) durante a inicialização e e matenha/guarde o objeto
    *     retornado dentro do AplicationContext - ou seja, temos um singleton!
    * */
    @Bean
    public OpenAPI customOpenAPI() {

        // Define esquema de segurança JWT
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                // Informações da API
                .info(new Info()
                        .title("Agro Telemetria API")
                        .description("API para monitoramento de sensores agrícolas")
                        .version("1.0.0"))

                // Configuração de segurança JWT no Swagger
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                // Define como o token será enviado
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}