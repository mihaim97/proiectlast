package com.ubb.ro.proiect1.dao.session;

import com.ubb.ro.proiect1.entity.SessionEntity;

import java.util.List;

public interface SessionDAO {

    void persist(SessionEntity sessionEntity);

    void remove(SessionEntity sessionEntity);

    SessionEntity findById(int id);

    List<SessionEntity> findBySemesterAndUniversityYear(String universityYear, Integer semester);

    List<SessionEntity> queryForYear(int year);

    List<SessionEntity> queryAll();

    List<SessionEntity> querySessionForTeacher(int teacherId);

}
