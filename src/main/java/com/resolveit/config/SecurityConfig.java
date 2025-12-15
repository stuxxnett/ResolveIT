package com.resolveit.config;

import com.resolveit.security.JwtFilter;
import com.resolveit.security.JwtUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // 1️⃣ Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2️⃣ JwtFilter Bean
    @Bean
    public JwtFilter jwtFilterBean(JwtUtil jwtUtil) {
        return new JwtFilter(jwtUtil);
    }

    // 3️⃣ Register Filter for API Paths
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(JwtFilter filter) {

        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();

        registration.setFilter(filter);
        registration.addUrlPatterns("/api/complaints");
        registration.addUrlPatterns("/api/complaints/*");
        registration.addUrlPatterns("/api/admin/*");


        return registration;
    }
}
