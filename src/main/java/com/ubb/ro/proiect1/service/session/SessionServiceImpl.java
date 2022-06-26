package com.ubb.ro.proiect1.service.session;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.dao.classentity.ClassEntityDAO;
import com.ubb.ro.proiect1.dao.session.SessionDAO;
import com.ubb.ro.proiect1.dto.session.*;
import com.ubb.ro.proiect1.dto.user.StudentDTO;
import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.SessionGrade;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.security.util.exception.SessionGradeException;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private ClassEntityDAO classEntityDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void addNewSession(SessionDTO sessionDTO) {
        SessionEntity session = new SessionEntity();
        if(sessionDTO.getDateEnd().isBefore(sessionDTO.getDateStart())) {
            throw new SessionGradeException("Data de sfarsit nu poate sa fie inainte de data de inceput");
        }
        session.setDateCreated(LocalDate.now());
        session.setDateStart(sessionDTO.getDateStart());
        session.setDateEnd(sessionDTO.getDateEnd());
        session.setSessionType(sessionDTO.getSessionType());
        System.out.println(sessionDTO.getUniversityYear());
        session.setSemester(sessionDTO.getSemester());
        session.setUniversityYear(sessionDTO.getUniversityYear());
        this.sessionDAO.persist(session);
    }

    @Override
    @Transactional
    public void addClassToSession(Authentication authentication, int sessionId, int classId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        ClassEntity classEntity = this.classEntityDAO.findById(classId);
        ClassEntity exist = session.getClasses().stream().filter(cls->cls.getId() == classEntity.getId())
                .findFirst().orElse(null);
        if(exist == null) {
            session.getClasses().add(classEntity);
        }
    }

    @Override
    @Transactional
    public void addSessionStudent(Authentication authentication, int sessionId, int userId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        User user = this.userDAO.searchById(userId);
        User exist = session.getStudents().stream().filter(std->std.getId() == user.getId())
                .findFirst().orElse(null);
        if(exist == null) {
            session.getStudents().add(user);
        }
    }

    @Override
    @Transactional
    public SessionDTOView querySession(String universityYear, Integer semester) {
        SessionEntity session = SingleResultFromList.getSingleResult(
                this.sessionDAO.findBySemesterAndUniversityYear(universityYear, semester));
        if(session != null) {
            return this.toView(session);
        }
        return new SessionDTOView();
    }

    @Override
    @Transactional
    public List<SimpleSessionViewDTO> queryAllSession(Authentication authentication) {
        return this.sessionDAO.queryAll().stream().map(this::toView2).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSession(Authentication authentication, int sessionId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        this.sessionDAO.remove(session);
    }

    @Override
    @Transactional
    public List<ClassEntityDTOView> sessionClasses(Authentication authentication, int sessionId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        return session.getClasses().stream().map(this::toClassesInSession).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<StudentDTO> studentsInSession(Authentication authentication, int sessionId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        return session.getStudents().stream().map(this::studentInSession).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteStudentInSession(Authentication authentication, int sessionId, int studentId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        session.getStudents().removeIf(el->el.getId() == studentId);
    }

    @Override
    @Transactional
    public void deleteClassInSession(Authentication authentication, int sessionId, int classId) {
        SessionEntity session = this.sessionDAO.findById(sessionId);
        session.getClasses().removeIf(el->el.getId() == classId);
    }

    private ClassEntityDTOView toClassesInSession(ClassEntity classEntity) {
        ClassEntityDTOView dto = new ClassEntityDTOView();
        dto.setId(classEntity.getId());
        dto.setName(classEntity.getName());
        dto.setCredits(classEntity.getCredits());
        dto.setTeacherName(classEntity.getTeacherId().getFullName());
        dto.setSemester(classEntity.getSemester());
        return dto;
    }

    private StudentDTO studentInSession(User user) {
        StudentDTO studentInSession = new StudentDTO();
        studentInSession.setName(user.getFullName());
        studentInSession.setId(user.getId());
        studentInSession.setYear(user.getYear());
        studentInSession.setProgram(user.getProgram());
        return studentInSession;
    }

    private SimpleSessionViewDTO toView2(SessionEntity session) {
        SimpleSessionViewDTO dto = new SimpleSessionViewDTO();
        dto.setDateCreated(session.getDateCreated());
        dto.setDateEnd(session.getDateEnd());
        dto.setDateStart(session.getDateStart());
        dto.setId(session.getId());
        dto.setSessionType(session.getSessionType());
        dto.setUniversityYear(session.getUniversityYear());
        dto.setSemester(session.getSemester());
        return dto;
    }

    private SessionDTOView toView(SessionEntity session) {
        SessionDTOView dto = new SessionDTOView();
        dto.setDateCreated(session.getDateCreated());
        dto.setDateEnd(session.getDateEnd());
        dto.setDateStart(session.getDateStart());
        dto.setId(session.getId());
        dto.setSessionType(session.getSessionType());
        dto.setUniversityYear(session.getUniversityYear());
        dto.setSemester(session.getSemester());
        dto.setSessionGrades(session.getSessionGrades().stream().map(this::toSessionGrade).collect(Collectors.toList()));
        dto.setClassEntities(session.getClasses().stream().map(this::toClassEntity).collect(Collectors.toList()));
        return dto;
    }

    private SessionGradeDTOView toSessionGrade(SessionGrade sessionGrade) {
        SessionGradeDTOView dto = new SessionGradeDTOView();
        dto.setGrade(sessionGrade.getGrade());
        dto.setClassName(sessionGrade.getClassId().getName());
        dto.setPromotionDate(sessionGrade.getPromotionDate());
        dto.setId(sessionGrade.getId());
        dto.setStudentName(sessionGrade.getStudentId().getFullName());
        return dto;
    }

    private ClassEntityDTOView toClassEntity(ClassEntity classEntity) {
        ClassEntityDTOView dto =  new ClassEntityDTOView();
        dto.setCredits(classEntity.getCredits());
        dto.setName(classEntity.getName());
        dto.setSemester(classEntity.getSemester());
        dto.setTeacherName(classEntity.getTeacherId().getFullName());
        dto.setId(classEntity.getId());
        return dto;
    }


}
