package com.tastytown.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean //Bean is provided by Factory Method
    OpenAPI openAPI() {
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        var info = new Info()
                .title("Tasty Town")
                .version("V2")
                .description("A Web Application for Ordering Foods!");
                
        return info;
    }
}

/*
 * To provide the object of Predefined  class or Predefined interface to Spring;
 *  we need to provide the Bean
 * 
 * It can be provided in 3 different ways
 * 
 */
