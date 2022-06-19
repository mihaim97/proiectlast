package com.ubb.ro.proiect1.dao;

import com.ubb.ro.proiect1.entity.Role;
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
    public void persist(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void persistRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(role);
    }

    @Override
    public List<User> searchByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select distinct us from User us left join fetch us.roles where us.username = :username", User.class)
                .setParameter("username", username).getResultList();
    }

    @Override
    public User searchById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<User> searchByIdList(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where id = :id", User.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<User> queryUser() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }

}
