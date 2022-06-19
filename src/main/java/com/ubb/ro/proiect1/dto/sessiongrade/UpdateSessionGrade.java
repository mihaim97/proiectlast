package com.ubb.ro.proiect1.dto.sessiongrade;

import java.time.LocalDateTime;

public class UpdateSessionGrade {

    private int gradeId;

    private int grade;

    private LocalDateTime date;

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
