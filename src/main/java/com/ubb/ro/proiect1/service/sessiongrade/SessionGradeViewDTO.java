package com.ubb.ro.proiect1.service.sessiongrade;


import java.time.LocalDate;

public class SessionGradeViewDTO {

    private float grade;

    private LocalDate promotionDate;

    private String className;

    public SessionGradeViewDTO(float grade, LocalDate promotionDate, String className) {
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

    public LocalDate getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(LocalDate promotionDate) {
        this.promotionDate = promotionDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
