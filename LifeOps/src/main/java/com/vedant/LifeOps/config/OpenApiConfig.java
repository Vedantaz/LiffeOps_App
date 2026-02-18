package com.vedant.LifeOps.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LifeOps Task Management API")
                        .version("1.0")
                        .description("Spring Boot REST API for managing tasks with pagination, filtering and DTO architecture"));
    }


}
