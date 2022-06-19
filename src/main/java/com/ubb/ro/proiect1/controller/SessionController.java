package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.dto.session.*;
import com.ubb.ro.proiect1.dto.user.StudentDTO;
import com.ubb.ro.proiect1.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/session")
    public ResponseEntity<SessionDTOView> sessionForSemesterAndUniversityYear(Authentication authentication,
                                                                              @RequestParam String universityYear,
                                                                              @RequestParam Integer semester) {
        return ResponseEntity.ok(this.sessionService.querySession(universityYear, semester));
    }

    @GetMapping("/session/all")
    public ResponseEntity<List<SimpleSessionViewDTO>> queryAllSession(Authentication authentication) {
        return ResponseEntity.ok(this.sessionService.queryAllSession(authentication));
    }

    @DeleteMapping("session/delete")
    public ResponseEntity<String> deleteSession(Authentication authentication,
                                                @RequestParam(name = "sessionId") int sessionId) {
        this.sessionService.deleteSession(authentication, sessionId);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @GetMapping("session/classes")
    public ResponseEntity<List<ClassEntityDTOView>> classesInSession(Authentication authentication,
                                                                      @RequestParam(name = "sessionId") int sessionId) {
        return ResponseEntity.ok(this.sessionService.sessionClasses(authentication, sessionId));
    }

    @GetMapping("session/student")
    public ResponseEntity<List<StudentDTO>> studentInSession(Authentication authentication,
                                                             @RequestParam(name = "sessionId") int sessionId) {
        return ResponseEntity.ok(this.sessionService.studentsInSession(authentication, sessionId));
    }

    @DeleteMapping("session/delete/student")
    public ResponseEntity<String> deleteStudentInSession(Authentication authentication,
                                                @RequestParam(name = "sessionId") int sessionId,
                                                         @RequestParam(name = "studentId") int studentId) {
        this.sessionService.deleteStudentInSession(authentication, sessionId, studentId);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @DeleteMapping("session/delete/class")
    public ResponseEntity<String> deleteClassInSession(Authentication authentication,
                                                         @RequestParam(name = "sessionId") int sessionId,
                                                         @RequestParam(name = "classId") int classId) {
        this.sessionService.deleteClassInSession(authentication, sessionId, classId);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @PostMapping("session/new")
    public ResponseEntity<String> newSession(Authentication authentication, @RequestBody SessionDTO sessionDTO) {
        this.sessionService.addNewSession(sessionDTO);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @PostMapping("session/addClass")
    public ResponseEntity<String> addClassToSession(Authentication authentication,
                                                    @RequestBody NewClassToSession dto) {
        this.sessionService.addClassToSession(authentication, dto.getSessionId(), dto.getClassId());
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @PostMapping("session/addStudent")
    public ResponseEntity<String> addStudent(Authentication authentication,
                                                    @RequestBody NewStudentToSession dto) {
        this.sessionService.addSessionStudent(authentication, dto.getSessionId(), dto.getStudentId());
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }





}
