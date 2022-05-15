package com.ubb.ro.proiect1.config;

import com.ubb.ro.proiect1.security.MethodeInjectResolver;
import com.ubb.ro.proiect1.security.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserAuth userAuth;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        userAuth.setName("Mihai");
        resolvers.add(new MethodeInjectResolver(userAuth));
    }

}
