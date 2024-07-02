package com.lenora.staj.websocket.rest.filter;

import com.lenora.staj.websocket.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JWTFilter extends HttpFilter {

    private final JWTUtil jwtUtil;


    public JWTFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    private final List<String> excludeUrls = Arrays.asList("/api/auth/login", "/api/auth/register");

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String path = request.getRequestURI();


        // Skip JWT check for excluded URLs and OPTIONS requests
        if (excludeUrls.contains(path) || "OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        }

        String token = authorizationHeader.substring(7);
        String user = jwtUtil.extractUsername(token);
        //TODO check if user actually exists
        if (StringUtils.isBlank(user)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Token");
            return;
        }

        request.setAttribute("username", user);

        chain.doFilter(request, response);
    }
}
