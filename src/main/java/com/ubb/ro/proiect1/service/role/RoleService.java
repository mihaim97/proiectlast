package com.ubb.ro.proiect1.service.role;

import org.springframework.security.core.Authentication;

public interface RoleService {

    void hasAdminRole(Authentication authentication);

}
