package com.ubb.ro.proiect1.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubb.ro.proiect1.security.util.JwtProperties;
import com.ubb.ro.proiect1.security.util.LogIn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
       this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LogIn logInModel = null;
        try {
            logInModel = new ObjectMapper().readValue(request.getInputStream(), LogIn.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(logInModel.getUsername(),
                        logInModel.getPassword(), new ArrayList<>());
        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        PersonalUserDetails personalUserDetails = (PersonalUserDetails)authResult.getPrincipal();
        String token = JWT.create()
                            .withSubject(personalUserDetails.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                            .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.HEADER);
        response.addHeader(JwtProperties.HEADER, JwtProperties.TOKEN_PREFIX + token);
        //Expose user
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.HEADER_USER);
        response.addHeader(JwtProperties.HEADER_USER, personalUserDetails.getUsername());
        // Return credential, needs to load in PersonalUserDetailsService..
        // Expose roles
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.ROLES);
        response.addHeader(JwtProperties.ROLES, personalUserDetails.getAuthorities().toString());
        System.out.println("Success ---");
    }
}
