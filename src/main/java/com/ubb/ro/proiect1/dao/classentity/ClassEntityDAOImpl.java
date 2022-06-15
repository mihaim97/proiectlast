package com.ubb.ro.proiect1.dao.classentity;

import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.User;
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

    @Override
    public List<ClassEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ClassEntity ce", ClassEntity.class)
                .getResultList();
    }

    @Override
    public List findPercentage(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT TOP(1)  CAST(((Select COUNT(*) from Session_grades\n" +
                        "INNER JOIN pr_classes as ClassesInnerQuery ON Session_grades.id_classes = ClassesInnerQuery.id\n" +
                        "where ClassesInnerQuery.id =:id\n" +
                        "and Session_grades.grade < 5) / CAST((Select COUNT(*) from Session_grades\n" +
                        "INNER JOIN pr_classes as ClassesInnerQuery ON Session_grades.id_classes = ClassesInnerQuery.id\n" +
                        "where ClassesInnerQuery.id =:id) AS DECIMAL))*100 AS DECIMAL(18, 2)) As PERCENTAGE\n" +
                        "FROM Session_grades AS SessionGr\n" +
                        "INNER JOIN pr_classes as Classes ON SessionGr.id_classes = Classes.id\n" +
                        "where classes.id =:id", ClassEntity.class)
                .getResultList();
    }



}
