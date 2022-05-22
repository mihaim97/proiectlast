package com.ubb.ro.proiect1.service.role;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public void hasAdminRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new RuntimeException("Not allow");
        }
    }
}
