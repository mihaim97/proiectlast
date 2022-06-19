package com.ubb.ro.proiect1.dto.session;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionGradeDTOView {

    private int id;

    private float grade;

    private LocalDateTime promotionDate;

    private String className;

    private String studentName;

    public SessionGradeDTOView() {
    }

    public SessionGradeDTOView(int id, float grade, LocalDateTime promotionDate, String className, String studentName) {
        this.id = id;
        this.grade = grade;
        this.promotionDate = promotionDate;
        this.className = className;
        this.studentName = studentName;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
