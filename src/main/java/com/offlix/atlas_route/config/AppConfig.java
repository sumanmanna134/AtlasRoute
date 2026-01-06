package com.offlix.atlas_route.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class AppConfig {

    private static String baseUrl;
    @Value("${atlas.base-url:}")
    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    @PostConstruct
    public void init() {
        // 2. Check if the injected value is empty or invalid
        if (baseUrl == null || baseUrl.isBlank() || !baseUrl.startsWith("http")) {
            try {
                baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            } catch (Exception e) {
                baseUrl = "http://localhost:8080";
            }
        }
    }

    public static String getServerUrl() {
        return baseUrl;
    }
}
