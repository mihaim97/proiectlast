package com.ubb.ro.proiect1.service.session;

import com.ubb.ro.proiect1.dto.session.*;
import com.ubb.ro.proiect1.dto.user.StudentDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SessionService {

    void addNewSession(SessionDTO sessionDTO);

    void addClassToSession(Authentication authentication, int sessionId, int classId);

    void addSessionStudent(Authentication authentication, int sessionId, int userId);

    SessionDTOView querySession(String universityYear, Integer semester);

    List<SimpleSessionViewDTO> queryAllSession(Authentication authentication);

    void deleteSession(Authentication authentication, int sessionId);

    List<ClassEntityDTOView> sessionClasses(Authentication authentication, int sessionId);

    List<StudentDTO> studentsInSession(Authentication authentication, int sessionId);

    void deleteStudentInSession(Authentication authentication, int sessionId, int studentId);

    void deleteClassInSession(Authentication authentication, int sessionId, int classId);

}
