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
    private final List<String>  websocketURLs = Arrays.asList("/api/ws");
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String path = request.getRequestURI();
        // Skip JWT check for excluded URLs and OPTIONS requests
        if (excludeUrls.contains(path) || "OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        String token = "";
        if (path.contains("/api/ws")) {
            String query = request.getQueryString();
            if (query != null && query.contains("token=")) {
                String t = query; //"abc=123&def=456"
                String tokenIdentifier = "token=";
                int tokenStartIndex = t.indexOf(tokenIdentifier) + tokenIdentifier.length();
                String tokenCut = t.substring(tokenStartIndex);
                int tokenEnd = tokenCut.indexOf("&");
                if(tokenEnd != -1)
                    token = tokenCut.substring(0, tokenEnd);
                else
                    token = tokenCut;
            }

        } else {
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return;
            }

            token = authorizationHeader.substring(7);
        }
        try {
            String user = jwtUtil.extractUsername(token);
        }
        catch (Exception e) {
            throw e;
        }
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
