package com.ubb.ro.proiect1.dto.session;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class SessionDTO {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateStart;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateEnd;

    private String sessionType;

    private String universityYear;

    private Integer semester;

    public SessionDTO() {
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

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
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
