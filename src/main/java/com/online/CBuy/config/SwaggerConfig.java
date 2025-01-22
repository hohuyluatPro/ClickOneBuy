package com.online.CBuy.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation for ClickOneBuy")
                        .version("1.0.0")
                        .description("This is the API documentation for the CBuy project.")
                        .contact(new Contact()
                                .name("Hồ Huy Luật")
                                .email("hohuyluat1@gmail.com")
                                .url("https://example.com")))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
        .components(new Components()
                .addSecuritySchemes("BearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                ;
    }
}
