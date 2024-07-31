package ru.babim.template.activity.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@OpenAPIDefinition
@SecurityScheme(
        name = "Authorization",
        bearerFormat = "JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER

)
public class OpenAPIConfig {
    @Value("${app.version}")
    private String appVersion;
    @Value("${spring.application.name}")
    private String appName;
    private static final String APP_DESCRIPTION = """
            %s API - Activity management service.
            - CRUD operations for activities
            - Adding an activity for an employee
            - Getting a list of employee activities
            - Reminders about upcoming activities
            """;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public OpenAPI openAPI() {
        final String apiAppName = appName.substring(0, 1).toUpperCase() + appName.substring(1);
        return new OpenAPI()
                .info(new Info().title("%s API".formatted(apiAppName))
                        .version(appVersion)
                        .description(APP_DESCRIPTION.formatted(apiAppName)));
    }
}
