package com.ubb.ro.proiect1.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubb.ro.proiect1.entity.Role;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.security.util.LogIn;
import com.ubb.ro.proiect1.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ValidateTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            System.out.println("Header  " + headerNames.nextElement());
        }
        //System.out.println(request.getHeaderNames().toString());
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token == null || !token.startsWith("Bearer ")) {
            return;
        }
        this.validateToken(token, (HttpServletResponse) servletResponse);
    }

    private void validateToken(String token, HttpServletResponse response) {
        String username = JWT.require(Algorithm.HMAC512(("SecretKeyProiect").getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();
        System.out.println("validate");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(username != null) {
            response.setStatus(200);
        }else {
            response.setStatus(401);
        }
    }

}
