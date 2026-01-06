package com.offlix.atlas_route.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
/**
 * OpenAPI/Swagger configuration.
 * Access documentation at: http://localhost:8080/swagger-ui.html
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI routingServiceOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Enterprise Routing Service API")
                        .description("Routing service with multiple algorithms and providers")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Routing Team")
                        )
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")
                        )
                ).servers(List.of(
                        new Server()
                                .url(AppConfig.getServerUrl())
                                .description("Local development"),
                        new Server()
                                .url(AppConfig.getServerUrl())
                                .description("Production")
                ));
    }
}
