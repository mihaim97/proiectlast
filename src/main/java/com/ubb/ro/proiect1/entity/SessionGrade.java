package com.ubb.ro.proiect1.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "session_grades")
public class SessionGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "grade")
    private float grade;

    @Column(name = "promotion_date")
    private LocalDate promotionDate;

    @ManyToOne
    @JoinColumn(name = "id_classes")
    private ClassEntity classId;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private SessionEntity sessionId;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private User studentId;

    public SessionGrade(int id, float grade, LocalDate promotionDate, ClassEntity classId, SessionEntity sessionId, User studentId) {
        this.id = id;
        this.grade = grade;
        this.promotionDate = promotionDate;
        this.classId = classId;
        this.sessionId = sessionId;
        this.studentId = studentId;
    }

    public SessionGrade(float grade, LocalDate promotionDate, ClassEntity classId, SessionEntity sessionId, User studentId) {
        this.id = id;
        this.grade = grade;
        this.promotionDate = promotionDate;
        this.classId = classId;
        this.sessionId = sessionId;
        this.studentId = studentId;
    }



    public SessionGrade() {
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
}
