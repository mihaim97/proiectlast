package com.ubb.ro.proiect1.dto.classentity;

public class ClassEntityDTO2 {

    private int id;

    private String name;

    private int semester;

    private int credits;

    private String teacherName;

    public ClassEntityDTO2(int id, String name, int semester, int credits, String teacherName) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.teacherName = teacherName;
    }

    public ClassEntityDTO2(){

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
