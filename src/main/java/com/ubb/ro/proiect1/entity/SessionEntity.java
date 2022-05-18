package com.ubb.ro.proiect1.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pr_session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "session_type")
    private String sessionType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sessionId")
    private List<SessionGrade> sessionGrades;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "pr_session_calsses",
            joinColumns = {@JoinColumn(name = "id_session")},
            inverseJoinColumns = {@JoinColumn(name = "id_classes")}
    )
    private List<ClassEntity> classes;

    public SessionEntity() {
    }

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

    public List<SessionGrade> getSessionGrades() {
        return sessionGrades;
    }

    public void setSessionGrades(List<SessionGrade> sessionGrades) {
        this.sessionGrades = sessionGrades;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }
}
