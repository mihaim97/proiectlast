package com.ubb.ro.proiect1.dao;

import com.ubb.ro.proiect1.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> searchByName(String username);

}
