package com.example.Crud;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//
//Run your application
//
//Go to:
//
//http://localhost:8080/swagger-ui.html
//
//
//or
//
//http://localhost:8080/swagger-ui/index.html
//
//
//Click Authorize 🔐
//
//Enter your username and password (admin / admin123, etc.)
//
//All secured endpoints (@PreAuthorize, etc.) will now prompt via Swagger for authentication.


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        final String basicAuthScheme = "basicAuth";

        return new OpenAPI()
            .info(new Info()
                .title("Person Management API")
                .version("1.0")
                .description("Spring Boot + Spring Security (Basic Auth) + Swagger"))
            .addSecurityItem(new SecurityRequirement().addList(basicAuthScheme))
            .components(new Components()
                .addSecuritySchemes(basicAuthScheme,
                    new SecurityScheme()
                        .name(basicAuthScheme)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")));
    }
}
