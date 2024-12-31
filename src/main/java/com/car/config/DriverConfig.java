package com.car.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Make this class a Spring configuration class
public class DriverConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define CORS settings for the '/api/driver' endpoints
        registry.addMapping("/api/driver/**") // Apply CORS to all API endpoints under "/api/driver"
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") // Allow these origins (frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials like cookies if needed
    }
}
