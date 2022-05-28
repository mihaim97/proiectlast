package com.ubb.ro.proiect1.dao.session;

import com.ubb.ro.proiect1.entity.SessionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionDAOImpl implements SessionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(SessionEntity sessionEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(sessionEntity);
    }

    @Override
    public void remove(SessionEntity sessionEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(sessionEntity);
    }

    @Override
    public SessionEntity findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from SessionEntity where id =:id", SessionEntity.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<SessionEntity> findBySemesterAndUniversityYear(String universityYear, Integer semester) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from SessionEntity" +
                        " where universityYear = :universityYear and semester = :semester",
                        SessionEntity.class)
                .setParameter("universityYear", universityYear)
                .setParameter("semester", semester)
                .getResultList();
    }

    @Override
    public List<SessionEntity> queryForYear(int year) {
        return null;
    }
}
