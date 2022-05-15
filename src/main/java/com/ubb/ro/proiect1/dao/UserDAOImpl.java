package com.ubb.ro.proiect1.dao;

import com.ubb.ro.proiect1.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> searchByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User us join fetch us.roles where us.username = :username", User.class)
                .setParameter("username", username).getResultList();
    }
}
