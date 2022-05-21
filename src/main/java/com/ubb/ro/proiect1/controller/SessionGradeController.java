package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.service.sessiongrade.SessionGradeDTO;
import com.ubb.ro.proiect1.service.sessiongrade.SessionGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class SessionGradeController {
    @Autowired
    private SessionGradeService sessionGradeService;

    @PostMapping(
            value = "/addSessionGrade", consumes = "application/json")
    public void createPerson(@RequestBody SessionGradeDTO sessionGradeDTO) {
        sessionGradeService.addSessionGrade(sessionGradeDTO);
    }

    @GetMapping("/sessionGrades/{id}")
    public SessionGradeDTO getAll(@PathVariable String id) {
        return this.sessionGradeService.findById(Integer.parseInt(id));

    }

}
