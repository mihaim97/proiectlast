package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.dto.sessiongrade.*;
import com.ubb.ro.proiect1.service.sessiongrade.SessionGradeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SessionGradeController {

    @Autowired
    private SessionGradeService sessionGradeService;

    @PostMapping(value = "/addsessiongrade", consumes = "application/json")
    public ResponseEntity<StudentGrade> createPerson(Authentication authentication, @RequestBody SessionGradeDTO sessionGradeDTO) {
        return ResponseEntity.ok(this.sessionGradeService.addSessionGrade(sessionGradeDTO));
    }

    @DeleteMapping(value = "/sessionGrade/delete", consumes = "application/json")
    public void deleteSessionGrade(Authentication authentication, @RequestParam(name = "sessionGradeId") int id) {
        this.sessionGradeService.delete(id);
    }

    @PatchMapping(value = "/sessionGrade/update", consumes = "application/json")
    public ResponseEntity<StudentGrade> createPerson(Authentication authentication, @RequestBody UpdateSessionGrade updateSessionGrade) {
        return ResponseEntity.ok(this.sessionGradeService.updateStudentGrade(authentication, updateSessionGrade));
    }

    @GetMapping("/sessionGrades/{id}")
    public SessionGradeDTO getAll(@PathVariable String id) {
        return this.sessionGradeService.findById(Integer.parseInt(id));
    }

    @GetMapping("/sessionGrades/all/teacher/classes")
    public ResponseEntity<List<TeacherSessionClasses>> allTeacherClassesInSessions(Authentication authentication) {
        return ResponseEntity.ok(this.sessionGradeService.allTeacherClasses(authentication));
    }

    @GetMapping("/grades")
    public ResponseEntity<List<SessionGradeViewDTO>> getAll(Authentication authentication) {
        return ResponseEntity.ok(this.sessionGradeService.getGradesForStudent(authentication));
    }

    @GetMapping("/sessionGrades/classid/{id}")
    public List<SessionGradeViewDTO> getPromotedStudentsByClassId(@PathVariable String id) {
        return this.sessionGradeService.getPromotedStudentsByClassId(Integer.parseInt(id));
    }

    @GetMapping("/sessionGrades/failed/classid/{id}")
    public List<SessionGradeViewDTO> getFailedStudentsByClassId(@PathVariable String id) {
        return this.sessionGradeService.getFailedStudentsByClassId(Integer.parseInt(id));
    }

}
