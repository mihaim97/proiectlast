package com.ubb.ro.proiect1.service.sessiongrade;


import com.ubb.ro.proiect1.dto.sessiongrade.SessionGradeDTO;
import com.ubb.ro.proiect1.dto.sessiongrade.SessionGradeViewDTO;
import com.ubb.ro.proiect1.dto.sessiongrade.TeacherSessionClasses;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SessionGradeService {

    void addSessionGrade(SessionGradeDTO sessionGrade);

    void delete(int id);

    SessionGradeDTO findById(int id);

    List<SessionGradeViewDTO> getGradesForStudent(Authentication authentication);

    List<TeacherSessionClasses> allTeacherClasses(Authentication authentication);

}
