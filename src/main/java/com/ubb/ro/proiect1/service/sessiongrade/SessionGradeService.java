package com.ubb.ro.proiect1.service.sessiongrade;


import com.ubb.ro.proiect1.dto.sessiongrade.*;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SessionGradeService {

    void addSessionGrade(SessionGradeDTO sessionGrade);

    void delete(int id);

    SessionGradeDTO findById(int id);

    StudentGrade updateStudentGrade(Authentication authentication, UpdateSessionGrade updateSessionGrade);

    List<SessionGradeViewDTO> getGradesForStudent(Authentication authentication);

    List<TeacherSessionClasses> allTeacherClasses(Authentication authentication);

}
