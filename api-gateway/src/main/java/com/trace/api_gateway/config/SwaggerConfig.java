package com.trace.api_gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(
            SwaggerUiConfigProperties swaggerUiConfigProperties,
            RouteDefinitionLocator locator) {

        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();

        urls.add(new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                "user-service",
                "/user-service/v3/api-docs",
                "User Service"
        ));
        urls.add(new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                "transaction-service",
                "/transaction-service/v3/api-docs",
                "Transaction Service"
        ));

        swaggerUiConfigProperties.setUrls(urls);
        return List.of();
    }
}