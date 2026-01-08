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

    // ✅ Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ CREATE JwtFilter BEAN
    @Bean
    public JwtFilter jwtFilter(JwtUtil jwtUtil) {
        return new JwtFilter(jwtUtil);
    }

    // ✅ REGISTER FILTER
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {

        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();

        registration.setFilter(jwtFilter);
        registration.addUrlPatterns(
                "/api/complaints/*",
                "/api/admin/*"
        );

        return registration;
    }
}
