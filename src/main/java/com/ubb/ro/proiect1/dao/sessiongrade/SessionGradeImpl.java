package com.ubb.ro.proiect1.dao.sessiongrade;

import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.SessionGrade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessionGradeImpl implements SessionGradeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(SessionGrade sessionGradeEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(sessionGradeEntity);
    }

    @Override
    public void remove(SessionGrade sessionGradeEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(sessionGradeEntity);
    }

    @Override
    public SessionGrade findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from SessionGrade where id =:id", SessionGrade.class)
                .setParameter("id", id).getSingleResult();
    }
}
