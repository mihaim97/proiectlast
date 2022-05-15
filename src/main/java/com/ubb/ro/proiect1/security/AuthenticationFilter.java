package com.ubb.ro.proiect1.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubb.ro.proiect1.entity.Role;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.security.config.SecureURL;
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
import java.util.HashSet;
import java.util.stream.Collectors;

public class AuthenticationFilter implements Filter {

    private UserAuth userAuth;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public AuthenticationFilter(UserAuth userAuth, UserService userService,
                                PasswordEncoder passwordEncoder) {
        this.userAuth = userAuth;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String body = this.getDataFromInputStream(request);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        LogIn logIn = mapper.readValue(body, LogIn.class);
        System.out.println(logIn);
        User user = this.userService.loadUser(logIn.getUsername());
        if(passwordEncoder.matches(logIn.getPassword(), user.getPassword())) {
            userAuth.setName(user.getUsername());
            userAuth.setRoles(new HashSet<>(user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet())));
            System.out.println("User authentication success");
            this.onSuccessfulAuthentication((HttpServletResponse) servletResponse, userAuth);
            //filterChain.doFilter(servletRequest, servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    private void onSuccessfulAuthentication(HttpServletResponse response, UserAuth userAuth) throws IOException {
        String token = JWT.create()
                .withSubject(userAuth.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 29000000))
                .sign(Algorithm.HMAC512(("SecretKeyProiect").getBytes()));
        response.setContentType("application/json");
        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + token);
        //Expose user
        response.addHeader("Access-Control-Expose-Headers", "Authority");
        response.addHeader("Authority", userAuth.getName());
        // Return credential, needs to load in PersonalUserDetailsService..
        // Expose roles
        //response.addHeader("Access-Control-Expose-Headers", JwtProperties.ROLES);
        //response.addHeader(JwtProperties.ROLES, personalUserDetails.getAuthorities().toString());
    }

    private String getDataFromInputStream(HttpServletRequest servletRequest) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("intra");
        for (String line; (line = bf.readLine()) != null; ) {
            System.out.println(line + " l");
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


}
