package com.lenora.staj.websocket.rest.config;

import com.lenora.staj.websocket.rest.filter.JWTFilter;
import com.lenora.staj.websocket.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Autowired
    private JWTUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<JWTFilter> jwtAuthFilter() {
        FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JWTFilter(jwtUtil));
        registrationBean.addUrlPatterns("/api/*"); // Apply filter to all URLs
        return registrationBean;
    }
}
