package com.ubb.ro.proiect1.dao.sessiongrade;
import com.ubb.ro.proiect1.entity.SessionGrade;

import java.util.List;

public interface SessionGradeDAO {

    void persist(SessionGrade sessionGradeEntity);

    void remove(SessionGrade sessionGradeEntity);

    SessionGrade findById(int id);

    List<SessionGrade> findForUser(int userId);

}
