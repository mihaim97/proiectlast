package com.ubb.ro.proiect1.service.session;

import com.ubb.ro.proiect1.dao.session.SessionDAO;
import com.ubb.ro.proiect1.dto.session.ClassEntityDTOView;
import com.ubb.ro.proiect1.dto.session.SessionDTO;
import com.ubb.ro.proiect1.dto.session.SessionDTOView;
import com.ubb.ro.proiect1.dto.session.SessionGradeDTOView;
import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.SessionGrade;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    @Transactional
    public void addNewSession(SessionDTO sessionDTO) {
        SessionEntity session = new SessionEntity();
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
    public SessionDTOView querySession(String universityYear, Integer semester) {
        SessionEntity session = SingleResultFromList.getSingleResult(
                this.sessionDAO.findBySemesterAndUniversityYear(universityYear, semester));
        if(session != null) {
            return this.toView(session);
        }
        return new SessionDTOView();
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
