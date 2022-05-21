package com.ubb.ro.proiect1.service.sessiongrade;

import com.ubb.ro.proiect1.entity.ClassEntity;
import com.ubb.ro.proiect1.entity.SessionEntity;
import com.ubb.ro.proiect1.entity.User;

import javax.persistence.*;
import java.time.LocalDate;

public class SessionGradeDTO {
    public SessionGradeDTO(int id, float grade, LocalDate promotionDate, ClassEntity classId, SessionEntity sessionId, User studentId) {
        this.id = id;
        this.grade = grade;
        this.promotionDate = promotionDate;
        this.classId = classId;
        this.sessionId = sessionId;
        this.studentId = studentId;
    }

    public SessionGradeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public LocalDate getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(LocalDate promotionDate) {
        this.promotionDate = promotionDate;
    }

    public ClassEntity getClassId() {
        return classId;
    }

    public void setClassId(ClassEntity classId) {
        this.classId = classId;
    }

    public SessionEntity getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionEntity sessionId) {
        this.sessionId = sessionId;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    private int id;

    private float grade;

    private LocalDate promotionDate;


    private ClassEntity classId;

    private SessionEntity sessionId;

    private User studentId;
}
