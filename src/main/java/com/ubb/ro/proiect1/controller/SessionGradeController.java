package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.service.sessiongrade.SessionGradeDTO;
import com.ubb.ro.proiect1.service.sessiongrade.SessionGradeService;
import com.ubb.ro.proiect1.service.sessiongrade.SessionGradeViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class SessionGradeController {

    @Autowired
    private SessionGradeService sessionGradeService;


    @PostMapping(value = "/addsessionsrade", consumes = "application/json")
    public void createPerson(@RequestBody SessionGradeDTO sessionGradeDTO) {
        sessionGradeService.addSessionGrade(sessionGradeDTO);
    }

    @GetMapping("/sessionGrades/{id}")
    public SessionGradeDTO getAll(@PathVariable String id) {
        return this.sessionGradeService.findById(Integer.parseInt(id));
    }

    @GetMapping("/grades")
    public ResponseEntity<List<SessionGradeViewDTO>> getAll(Authentication authentication) {
        return ResponseEntity.ok(this.sessionGradeService.getGradesForStudent(authentication));
    }

}
