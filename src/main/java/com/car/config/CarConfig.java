package com.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Make this class a Spring configuration class
public class CarConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define CORS settings for the '/api' endpoints
        registry.addMapping("/api/**") // Apply CORS to all API endpoints under "/api"
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") // Allow these origins (frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these methods
                .allowedHeaders("*") // Allow all headers
                .allowedHeaders("logged-in-user-id", "Content-Type")
                .allowCredentials(true); // Allow credentials like cookies if needed
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the '/car.css' and '/logo2.png' resources to their respective locations
        registry.addResourceHandler("/car.css", "/logo2.png","/uploads/**")
                //.addResourceHandler("")
                .addResourceLocations("classpath:/static/","file:uploads/");
                //.addResourceLocations();// Static resources in the 'static' directory
    }
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}

