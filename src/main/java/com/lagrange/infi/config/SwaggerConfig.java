package com.lagrange.infi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "OpenAPI Swagger Title",
                description = "OpenAPI description",
                version = "v1.0",
                contact = @Contact(
                        name = "IA",
                        email = "notuse@gmail.com"
                )
        )
)

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI openAPI(){
//        Info info = new Info()
//                .title("Title")
//                .version("v1.0.0")
//                .description("description");
//        return new OpenAPI()
//                .components(new Components())
//                .info(info);
//    }

}
