package com.ubb.ro.proiect1.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ubb.ro.proiect1.entity.Role;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.security.util.JwtProperties;
import com.ubb.ro.proiect1.service.role.RoleService;
import com.ubb.ro.proiect1.service.user.UserService;
import com.ubb.ro.proiect1.service.user.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ValidateController {

    @Autowired
    private TestService testService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/validate")
    public ResponseEntity<String> testController(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String username = JWT.require(Algorithm.HMAC512((JwtProperties.SECRET).getBytes()))
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
            if (username != null) {
                System.out.println("valid token");
                return ResponseEntity.ok("{\"response\":\"ok\"}");
            }
            throw new RuntimeException("Invalid token");
        } catch (Exception exception) {
            throw new RuntimeException("Invalid token");
        }
    }

    @PostMapping("/validate/data")
    public void expiredJwtAndData(@RequestHeader(value = "Authorization") String token,
                                  HttpServletResponse response) {
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                .getSubject();
        User user = this.userService.loadUser(username);
        System.out.println("Intra daaa");
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.HEADER);
        response.addHeader(JwtProperties.HEADER, JwtProperties.TOKEN_PREFIX + token);
        //Expose user
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.HEADER_USER);
        response.addHeader(JwtProperties.HEADER_USER, user.getUsername());
        // Return credential, needs to load in PersonalUserDetailsService..
        // Expose roles
        response.addHeader("Access-Control-Expose-Headers", JwtProperties.ROLES);
        response.addHeader(JwtProperties.ROLES, user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()).toString());

        // Expose Full Name
        response.addHeader("Access-Control-Expose-Headers", "FullAuthority");
        response.addHeader("FullAuthority", user.getFullName());

    }

    @GetMapping("/simple")
    public ResponseEntity<String> simpleMessage() {
        this.testService.performOperation();
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @GetMapping("/role/admin")
    public ResponseEntity<String> hasAdminRole(Authentication authentication) {
        this.roleService.hasAdminRole(authentication);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }


}
