package com.ubb.ro.proiect1.service;

import com.ubb.ro.proiect1.security.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserAuth userAuth;

    @Override
    public void doSomething() {
        System.out.println(userAuth.getName() + " " + this.getClass());

    }
}
