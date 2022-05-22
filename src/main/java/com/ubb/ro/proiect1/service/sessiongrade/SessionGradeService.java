package com.ubb.ro.proiect1.service.sessiongrade;


import org.springframework.security.core.Authentication;

import java.util.List;

public interface SessionGradeService {

    void addSessionGrade(SessionGradeDTO sessionGrade);

    void delete(int id);

    SessionGradeDTO findById(int id);

    List<SessionGradeViewDTO> getGradesForStudent(Authentication authentication);

}
