package com.ubb.ro.proiect1.dto.session;

public class NewStudentToSession {

    private int sessionId;

    private int studentId;

    public NewStudentToSession() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
