package com.ubb.ro.proiect1.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.security.util.JwtProperties;
import com.ubb.ro.proiect1.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtProperties.HEADER);
        if (token == null || !token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = getUsernamePasswordAuthenticationFromToken(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Authorize -- ");
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthenticationFromToken(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER);
        if (token != null) {
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                                .build()
                                .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                                .getSubject();
            if (username != null) {
                User user = userService.loadUser(username);
                PersonalUserDetails personalUserDetails = new PersonalUserDetails(user);
                UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        personalUserDetails.getUsername(), null, personalUserDetails.getAuthorities()
                );
                return passwordAuthenticationToken;
            }
            return null;
        }
        return null;

    }
}
