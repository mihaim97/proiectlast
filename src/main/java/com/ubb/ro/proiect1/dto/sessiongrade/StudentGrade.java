package com.ubb.ro.proiect1.dto.sessiongrade;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentGrade {

    private int id;

    private String studentName;

    private LocalDateTime promotionDate;

    private float grade;

    public StudentGrade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDateTime getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(LocalDateTime promotionDate) {
        this.promotionDate = promotionDate;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
