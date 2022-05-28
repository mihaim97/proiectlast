package com.ubb.ro.proiect1.dto.classentity;

public class ClassEntityDTO {

    private int id;

    private String name;

    private int semester;

     private int credits;

    private int teacherID;


    public ClassEntityDTO(int id, String name, int semester, int credits, int teacherID) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.teacherID = teacherID;
    }

    public ClassEntityDTO(){

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

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
}
