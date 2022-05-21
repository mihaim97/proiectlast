package com.ubb.ro.proiect1.dao.sessiongrade;
import com.ubb.ro.proiect1.entity.SessionGrade;

public interface SessionGradeDAO {
    void persist(SessionGrade sessionGradeEntity);

    void remove(SessionGrade sessionGradeEntity);

    SessionGrade findById(int id);
}
