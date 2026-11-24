package com.motoparts.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "MotoParts Hub API",
				description = "REST API for authentication, catalog management, vehicle fitments, shopping, orders, inventory, and seller administration.",
				version = "v1",
				contact = @Contact(name = "MotoParts Hub"),
				license = @License(name = "Proprietary")
		)
)
@SecurityScheme(
		name = "bearerAuth",
		description = "Enter a valid JWT access token.",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
