package com.ubb.ro.proiect1.security;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserAuth {

    private String name;

    private Set<String> roles;

    public UserAuth() {
    }

    public UserAuth(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
