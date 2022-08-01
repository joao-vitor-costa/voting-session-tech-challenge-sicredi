package com.joao.entrypoint.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Vote Session Manager - REST API")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs((new ExternalDocumentation()
                        .description("Vote Session Manager Wiki Documentation")
                        .url("https://github.com/joao-vitor-costa/voting-session-tech-challenge/tree/main/vote-session-manager")));
    }
}