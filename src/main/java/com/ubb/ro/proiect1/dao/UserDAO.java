package com.ubb.ro.proiect1.dao;

import com.ubb.ro.proiect1.entity.Role;
import com.ubb.ro.proiect1.entity.User;

import java.util.List;

public interface UserDAO {

    void persist(User user);

    void persistRole(Role role);

    List<User> searchByName(String username);

    User searchById(int id);

    List<User> searchByIdList(int id);

    List<User> queryUser();
}
