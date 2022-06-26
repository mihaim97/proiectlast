package com.ubb.ro.proiect1.service.sessiongrade;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.dao.classentity.ClassEntityDAO;
import com.ubb.ro.proiect1.dao.session.SessionDAO;
import com.ubb.ro.proiect1.dao.sessiongrade.SessionGradeDAO;
import com.ubb.ro.proiect1.dto.sessiongrade.*;
import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.SessionGrade;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.security.util.exception.SessionGradeException;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionGradeServiceImpl implements SessionGradeService {

    @Autowired
    private SessionGradeDAO sessionGradeDAO;

    @Autowired
    private ClassEntityDAO classEntityDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public StudentGrade addSessionGrade(SessionGradeDTO sessionGradeDTO) {
        ClassEntity classEntity = this.classEntityDAO.findById(sessionGradeDTO.getClassId());
        SessionEntity session = this.sessionDAO.findById(sessionGradeDTO.getSessionId());
        User user = this.userDAO.searchById(sessionGradeDTO.getStudentId());
        this.validateData(session, classEntity, sessionGradeDTO, user);
        SessionGrade sessionGrade = new SessionGrade(sessionGradeDTO.getGrade(), sessionGradeDTO.getPromotionDate(),
                classEntity, session, user);
        sessionGradeDAO.persist(sessionGrade);
        return this.createStudentGrade(sessionGrade);
    }

    @Override
    @Transactional
    public void delete(int id) {
        System.out.println(id);
        SessionGrade sessionGrade = this.sessionGradeDAO.findById(id);
        sessionGradeDAO.remove(sessionGrade);
    }

    @Override
    @Transactional
    public SessionGradeDTO findById(int id) {
        SessionGrade sessionGrade = sessionGradeDAO.findById(id);
        return new SessionGradeDTO(sessionGrade.getId(), sessionGrade.getGrade(), sessionGrade.getPromotionDate(),
                sessionGrade.getClassId().getId(), sessionGrade.getSessionId().getId(),
                sessionGrade.getStudentId().getId());
    }

    @Override
    @Transactional
    public StudentGrade updateStudentGrade(Authentication authentication, UpdateSessionGrade updateSessionGrade) {
        System.out.println(updateSessionGrade.getGradeId());
        SessionGrade sessionGrade = sessionGradeDAO.findById(updateSessionGrade.getGradeId());
        sessionGrade.setPromotionDate(updateSessionGrade.getDate());
        sessionGrade.setGrade(updateSessionGrade.getGrade());
        return new StudentGrade();
    }

    @Override
    @Transactional
    public List<SessionGradeViewDTO> getGradesForStudent(Authentication authentication) {
        User user = SingleResultFromList.getSingleResult(
                this.userDAO.searchByName(authentication.getName()));
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        if (grantedAuthorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {

        }
        if (user != null) {
            return this.sessionGradeDAO.findForUser(user.getId())
                    .stream().map(this::toDto).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public List<TeacherSessionClasses> allTeacherClasses(Authentication authentication) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByName(authentication.getName()));
        if (user != null) {
            List<SessionEntity> sessionEntities = this.sessionDAO.querySessionForTeacher(user.getId());
            System.out.println(sessionEntities.size());
            List<TeacherSessionClasses> sessionClasses = new ArrayList<>();
            for (SessionEntity session : sessionEntities) {
                sessionClasses.addAll(this.toTeacherSessionGrade(session));
            }
            return sessionClasses;
        }
        return new ArrayList<>();
    }

    private List<TeacherSessionClasses> toTeacherSessionGrade(SessionEntity session) {
        List<TeacherSessionClasses> list = new ArrayList<>();
        for (ClassEntity entity : session.getClasses()) {
            TeacherSessionClasses dto = this.toTeacherSessionGradeSimple(entity, session);
            List<SessionGrade> sessionGrades = session.getSessionGrades().stream()
                    .filter(grade -> grade.getClassId().getId() == entity.getId()).collect(Collectors.toList());
            dto.setStudentGrades(sessionGrades.stream().map(this::createStudentGrade).collect(Collectors.toList()));
            list.add(dto);
        }
        return list;
    }

    private StudentGrade createStudentGrade(SessionGrade sessionGrade) {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setGrade(sessionGrade.getGrade());
        studentGrade.setId(sessionGrade.getId());
        studentGrade.setStudentName(sessionGrade.getStudentId().getFullName());
        studentGrade.setPromotionDate(sessionGrade.getPromotionDate());
        return studentGrade;
    }

    private TeacherSessionClasses toTeacherSessionGradeSimple(ClassEntity classEntity, SessionEntity session) {
        TeacherSessionClasses dto = new TeacherSessionClasses();
        dto.setClassId(classEntity.getId());
        dto.setClassName(classEntity.getName());
        dto.setSemester(session.getSemester());
        dto.setSessionType(session.getSessionType());
        dto.setEnd(session.getDateEnd());
        dto.setStart(session.getDateStart());
        dto.setSessionYear(session.getUniversityYear());
        dto.setSessionId(session.getId());
        return dto;
    }
    public List<SessionGradeViewDTO> getPromotedStudentsByClassId ( int classId){
        return this.sessionGradeDAO.getPromotedStudentsByClassId(classId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SessionGradeViewDTO> getFailedStudentsByClassId ( int classId){
        return this.sessionGradeDAO.getFailedStudentsByClassId(classId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StatisticsDTO statisticsForTeacher(Authentication authentication, int sessionId, int classesId) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByName(authentication.getName()));
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        if(user != null) {
            SessionEntity session = this.sessionDAO.findById(sessionId);
            ClassEntity classEntity = this.classEntityDAO.findById(classesId);
            List<SessionGrade> sessionGrades = session.getSessionGrades().stream()
                    .filter(el->el.getClassId().getId() == classEntity.getId()).collect(Collectors.toList());
            int totalStudents = sessionGrades.size();
            int totalPromo = (int) sessionGrades.stream().filter(el -> el.getGrade() >= 5).count();
            statisticsDTO.setTotalStudents(totalStudents);
            statisticsDTO.setTotalPass(totalPromo);
            statisticsDTO.setTotalFail((int) sessionGrades.stream().filter(el -> el.getGrade() < 5).count());
            System.out.println(totalPromo + " " + totalStudents);
            if(totalStudents != 0 && totalPromo != 0) {
                statisticsDTO.setPromoRate(((float)totalPromo/totalStudents) * 100f);
            }else {
                statisticsDTO.setPromoRate(0f);
            }
            System.out.println(statisticsDTO.getPromoRate());
        }
        return statisticsDTO;
    }

    private SessionGradeViewDTO toDto (SessionGrade sessionGrade){
        SessionGradeViewDTO dto = new SessionGradeViewDTO();
        dto.setGrade(sessionGrade.getGrade());
        dto.setClassName(sessionGrade.getClassId().getName());
        dto.setPromotionDate(sessionGrade.getPromotionDate());
        return dto;
    }

    private void validateData (SessionEntity session, ClassEntity classEntity, SessionGradeDTO sessionGradeDTO, User
    user){
        User student = session.getStudents().stream().filter(el -> el.getId() == user.getId()).findFirst().orElse(null);
        SessionGrade userGrade = session.getSessionGrades().stream().filter(el -> el.getStudentId().getId() == user.getId())
                .findFirst().orElse(null);
        if (!session.getClasses().contains(classEntity)) {
            throw new SessionGradeException("Materia nu face parte din sesiunea curenta");
        }
        if (student == null) {
            throw new SessionGradeException("Studentul nu este inregistrat in sesiune");
        }
        if (userGrade != null) {
            throw new SessionGradeException("Studentul are o nota existenta");
        }
        LocalDate promotionDate = sessionGradeDTO.getPromotionDate().toLocalDate();
        if (!(promotionDate.isAfter(session.getDateStart()) && promotionDate.isBefore(session.getDateEnd()))) {
            throw new SessionGradeException("Data promovari nu este in intervalul sesiuni");
        }
    }

}
