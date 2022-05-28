package com.ubb.ro.proiect1.dto.sessiongrade;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionGradeViewDTO {

    private float grade;

    private LocalDateTime promotionDate;

    private String className;

    public SessionGradeViewDTO(float grade, LocalDateTime promotionDate, String className) {
        this.grade = grade;
        this.promotionDate = promotionDate;
        this.className = className;
    }

    public SessionGradeViewDTO() {
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
}
