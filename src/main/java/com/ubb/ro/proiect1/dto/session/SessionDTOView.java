package com.ubb.ro.proiect1.dto.session;

import java.time.LocalDate;
import java.util.List;

public class SessionDTOView {

    private int id;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private LocalDate dateCreated;

    private String sessionType;

    private String universityYear;

    private Integer semester;

    private List<SessionGradeDTOView> sessionGrades;

    private List<ClassEntityDTOView> classEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public List<SessionGradeDTOView> getSessionGrades() {
        return sessionGrades;
    }

    public void setSessionGrades(List<SessionGradeDTOView> sessionGrades) {
        this.sessionGrades = sessionGrades;
    }

    public List<ClassEntityDTOView> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntityDTOView> classEntities) {
        this.classEntities = classEntities;
    }

    public String getUniversityYear() {
        return universityYear;
    }

    public void setUniversityYear(String universityYear) {
        this.universityYear = universityYear;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
