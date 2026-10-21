package com.easyfactor.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "EasyFactor API",
        version = "0.1.0",
        description = "Backend for the EasyFactor Telegram Mini App: catalog browsing, manual invoicing and in-Telegram payments."))
public class OpenApiConfig {
}
