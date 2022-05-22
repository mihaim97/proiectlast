package com.ubb.ro.proiect1.service.sessiongrade;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.dao.classentity.ClassEntityDAO;
import com.ubb.ro.proiect1.dao.session.SessionDAO;
import com.ubb.ro.proiect1.dao.sessiongrade.SessionGradeDAO;
import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.SessionGrade;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionGradeServiceImpl implements SessionGradeService {

    @Autowired
    private SessionGradeDAO sessionGradeDAO;

    @Autowired
    private ClassEntityDAO  classEntityDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void addSessionGrade(SessionGradeDTO sessionGradeDTO) {
        ClassEntity classEntity = this.classEntityDAO.findById(sessionGradeDTO.getSessionId());
        SessionEntity session = this.sessionDAO.findById(sessionGradeDTO.getSessionId());
        User user = this.userDAO.searchById(sessionGradeDTO.getSessionId());
        SessionGrade sessionGrade = new SessionGrade(sessionGradeDTO.getGrade(), sessionGradeDTO.getPromotionDate(),
                classEntity, session, user);
        sessionGradeDAO.persist(sessionGrade);
    }

    @Override
    @Transactional
    public void delete(int id) {
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
    public List<SessionGradeViewDTO> getGradesForStudent(Authentication authentication) {
        User user = SingleResultFromList.getSingleResult(
                this.userDAO.searchByName(authentication.getName()));
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        if(grantedAuthorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {

        }
        if(user != null) {
            return this.sessionGradeDAO.findForUser(user.getId())
                    .stream().map(this::toDto).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private SessionGradeViewDTO toDto(SessionGrade sessionGrade) {
        SessionGradeViewDTO dto = new SessionGradeViewDTO();
        dto.setGrade(sessionGrade.getGrade());
        dto.setClassName(sessionGrade.getClassId().getName());
        dto.setPromotionDate(sessionGrade.getPromotionDate());
        return dto;
    }
}
