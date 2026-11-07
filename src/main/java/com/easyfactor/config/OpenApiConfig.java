package com.easyfactor.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "EasyFactor API",
        version = "0.1.0",
        description = "Backend for the EasyFactor Telegram Mini App: catalog browsing, manual invoicing and in-Telegram payments."))
@SecurityScheme(                            //this is added for swagger
        name = "bearerAuth",                //it is enabled "Authorize" part on swagger
        type = SecuritySchemeType.HTTP,     //so we can paste our token and login
        scheme = "bearer",
        bearerFormat = "JWT")
public class OpenApiConfig {
}
