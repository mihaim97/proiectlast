package com.ubb.ro.proiect1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pr_classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "semester")
    private int semester;

    @Column(name = "credits")
    private int credits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacherId;

    public ClassEntity(int id, String name, int semester, int credits, User teacherId) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    public ClassEntity(String name, int semester, int credits, User teacherId) {
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    public ClassEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public User getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(User teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return semester == that.semester && credits == that.credits && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, semester, credits);
    }
}
