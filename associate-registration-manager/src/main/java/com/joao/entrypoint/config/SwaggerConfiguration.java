package com.joao.entrypoint.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Associate - REST API")
                .description("Associate Registration Manager")
                .version("v1.0.0"));
    }
}
