package com.springboot.blog.config;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Operation(security = { @SecurityRequirement(name = "Authorization") })


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring Boot Blog REST API")
                        .description("Spring Boot Blog REST API Documentation")
                        .version("1.0")
                        .contact(new Contact().name("Roman Seveljov").email("roman084@gmx.com"))
                        .description("Blog API Wiki Documentation")
                        .license(new License().name("License of API").url("API License URL")));
    }

}
