package com.ubb.ro.proiect1.security.config;

import java.util.Set;

public interface SecureURL {

    boolean isAllowToResource(String resource);

    Set<String> getRolesForResource(String resource);

}
