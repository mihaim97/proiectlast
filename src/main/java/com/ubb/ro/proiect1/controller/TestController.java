package com.ubb.ro.proiect1.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ubb.ro.proiect1.security.util.JwtProperties;
import com.ubb.ro.proiect1.service.user.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/validate")
    public ResponseEntity<String> testController(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String username = JWT.require(Algorithm.HMAC512((JwtProperties.SECRET).getBytes()))
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
            if(username != null) {
                System.out.println("valid token");
                return ResponseEntity.ok("{\"response\":\"ok\"}");
            }
            throw new RuntimeException("Invalid token");
        }catch (Exception exception) {
            throw new RuntimeException("Invalid token");
        }
    }

    @GetMapping("/simple")
    public ResponseEntity<String> simpleMessage() {
        this.testService.performOperation();
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

}
