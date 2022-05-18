package com.ubb.ro.proiect1.dao.classentity;

import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassEntityDAOImpl implements ClassEntityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(ClassEntity sessionEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(sessionEntity);
    }

    @Override
    public void remove(ClassEntity sessionEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(sessionEntity);
    }

    @Override
    public ClassEntity findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ClassEntity where id =:id", ClassEntity.class)
                .setParameter("id", id).getSingleResult();
    }

}
