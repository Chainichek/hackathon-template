package ru.babim.template.helloworld.hellomicroservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {
    @Value("${app.version}")
    private String appVersion;
    @Value("${spring.application.name}")
    private String appName;
    private static final String APP_DESCRIPTION = """
            %s API - Hello World message service.
            - Getting a simple greeting message with or w/o parameters
            """;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public OpenAPI openAPI() {
        final String apiAppName = appName.substring(0, 1).toUpperCase() + appName.substring(1);
        return new OpenAPI()
                .info(new Info().title("%s API".formatted(apiAppName))
                        .version(appVersion)
                        .description(APP_DESCRIPTION.formatted(apiAppName)))
                .servers(List.of(new Server().url("/%s".formatted(appName)).description("Prod server")));
    }
}
