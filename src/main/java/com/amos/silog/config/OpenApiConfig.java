package com.amos.silog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Smart Issue Tracker API",
                version = "2.0",
                description = """
                        This API powers the Smart Issue Tracker system for logging, resolving, and tracking issues across teams. 
                        - Users can report issues, track progress, and receive notifications.
                        - Developers can update issue status and collaborate on resolutions.
                        - Admins can manage users, assignments, and trigger AI-based suggestions or resolution alerts.
                        """,
                contact = @Contact(
                        name = "Tertese Amos",
                        email = "terteseamos@gmail.com",
                        url = "https://github.com/aniwange33/smart-issue"
                )
        ),
        servers = @Server(url = "http://localhost:8080", description = "Local Development Server"),
        security = @SecurityRequirement(name = "Authorization")
)
@SecurityScheme(
        name = "Authorization",
        description = "JWT Bearer Token (use format: 'Bearer <token>')",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
