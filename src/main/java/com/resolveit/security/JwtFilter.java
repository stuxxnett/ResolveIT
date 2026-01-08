package com.resolveit.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = req.getHeader("Authorization");
        System.out.println("AUTH HEADER = " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(401, "Missing Token");
            return;
        }

        String token = authHeader.substring(7).trim();

        if (!jwtUtil.isTokenValid(token)) {
            ((HttpServletResponse) response).sendError(401, "Invalid Token");
            return;
        }

        chain.doFilter(request, response);
    }
}
