package com.ubb.ro.proiect1.service.sessiongrade;

import com.ubb.ro.proiect1.entity.SessionGrade;


public interface SessionGradeService {
    void addSessionGrade(SessionGradeDTO sessionGrade);
    void delete(SessionGradeDTO sessionGradeEntity);
    SessionGradeDTO findById(int id);

}
