package com.ubb.ro.proiect1.security.config;

import com.ubb.ro.proiect1.security.UserAuth;

import java.util.*;
import java.util.stream.Collectors;

public class SecureUrlConfiguration implements SecureURL {

    private final UserAuth userAuth;

    private Map<String, Set<String>> resourceAndRoles;

    private SecureUrlConfiguration(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public boolean isAllowToResource(String resource) {
        if(resourceAndRoles.containsKey(resource)) {
            Set<String> rolesAllow = new HashSet<>(resourceAndRoles.get(resource));
            rolesAllow.retainAll(userAuth.getRoles());
            return !rolesAllow.isEmpty();
        }
        return true;
    }

    @Override
    public Set<String> getRolesForResource(String resource) {
        return this.resourceAndRoles.get(resource);
    }

    public Map<String, Set<String>> getResourceAndRoles() {
        return resourceAndRoles;
    }

    public void setResourceAndRoles(Map<String, Set<String>> resourceAndRoles) {
        this.resourceAndRoles = resourceAndRoles;
    }

    public static class ResourceHelper {
        private final Map<String, Set<String>> resourceAndRoles;
        private UserAuth authenticatedUser;

        public ResourceHelper() {
            resourceAndRoles = new HashMap<>();
        }

        public ResourceHelper protectResource(String resource, String ...roles) {
            resourceAndRoles.put(resource, Arrays.stream(roles).collect(Collectors.toSet()));
            return this;
        }

        public ResourceHelper authenticatedUser(UserAuth userAuth) {
            authenticatedUser = userAuth;
            return this;
        }

        public SecureUrlConfiguration build() {
            SecureUrlConfiguration configuration = new SecureUrlConfiguration(authenticatedUser);
            configuration.setResourceAndRoles(resourceAndRoles);
            return configuration;
        }

    }

}
