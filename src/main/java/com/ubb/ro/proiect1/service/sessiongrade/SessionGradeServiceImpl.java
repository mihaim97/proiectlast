package com.ubb.ro.proiect1.service.sessiongrade;

import com.ubb.ro.proiect1.dao.sessiongrade.SessionGradeDAO;
import com.ubb.ro.proiect1.entity.SessionGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionGradeServiceImpl implements SessionGradeService {

    @Autowired
    private SessionGradeDAO sessionGradeDAO;

    @Override
    @Transactional
    public void addSessionGrade(SessionGradeDTO sessionGradeDTO) {
        SessionGrade sessionGrade = new SessionGrade(sessionGradeDTO.getId(), sessionGradeDTO.getGrade(),
                sessionGradeDTO.getPromotionDate(), sessionGradeDTO.getClassId(), sessionGradeDTO.getSessionId(),
                sessionGradeDTO.getStudentId());
        sessionGradeDAO.persist(sessionGrade);
    }

    @Override
    @Transactional
    public void delete(SessionGradeDTO sessionGradeDTO) {
        SessionGrade sessionGrade = new SessionGrade(sessionGradeDTO.getId(), sessionGradeDTO.getGrade(),
                sessionGradeDTO.getPromotionDate(), sessionGradeDTO.getClassId(), sessionGradeDTO.getSessionId(),
                sessionGradeDTO.getStudentId());
        sessionGradeDAO.remove(sessionGrade);
    }

    @Override
    @Transactional
    public SessionGradeDTO findById(int id) {
        SessionGrade sessionGrade = sessionGradeDAO.findById(id);
        SessionGradeDTO sessionGradeDTO = new SessionGradeDTO(sessionGrade.getId(), sessionGrade.getGrade(),
                sessionGrade.getPromotionDate(), sessionGrade.getClassId(), sessionGrade.getSessionId(),
                sessionGrade.getStudentId());
        return sessionGradeDTO;
    }
}
