package com.ubb.ro.proiect1.security;

import com.ubb.ro.proiect1.security.config.SecureURL;
import com.ubb.ro.proiect1.security.config.SecureUrlConfiguration;
import com.ubb.ro.proiect1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserAuth userAuth;

    @Autowired
    private UserService userService;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registerFilterAuthenticationBean() {
        FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        authenticationFilterFilterRegistrationBean.setFilter(authenticationFilter());
        authenticationFilterFilterRegistrationBean.addUrlPatterns("/login");
        authenticationFilterFilterRegistrationBean.setOrder(1);
        return authenticationFilterFilterRegistrationBean;
    }

/*    @Bean
    public FilterRegistrationBean<ValidateTokenFilter> registerFilterValidation() {
        FilterRegistrationBean<ValidateTokenFilter> authenticationFilterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        authenticationFilterFilterRegistrationBean.setFilter(new ValidateTokenFilter());
        authenticationFilterFilterRegistrationBean.addUrlPatterns("/validate");
        authenticationFilterFilterRegistrationBean.setOrder(1);
        return authenticationFilterFilterRegistrationBean;
    }*/

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter(userAuth, userService, passwordEncoder());
    }

    @Bean
    public SecureURL secureURLConfiguration() {
        return new SecureUrlConfiguration.ResourceHelper()
                .protectResource("/test", "ROLE_ADMIN", "ROLE_TEST")
                .authenticatedUser(userAuth)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
