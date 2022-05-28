package com.ubb.ro.proiect1.dto.sessiongrade;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionGradeDTO {

    private int id;

    private float grade;

    private LocalDateTime promotionDate;

    private int classId;

    private int sessionId;

    private int studentId;

    public SessionGradeDTO(int id, float grade, LocalDateTime promotionDate, int classId, int sessionId, int studentId) {
        this.id = id;
        this.grade = grade;
        this.promotionDate = promotionDate;
        this.classId = classId;
        this.sessionId = sessionId;
        this.studentId = studentId;
    }

    public SessionGradeDTO(float grade, LocalDateTime promotionDate, int classId, int sessionId, int studentId) {
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

    public LocalDateTime getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(LocalDateTime promotionDate) {
        this.promotionDate = promotionDate;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
