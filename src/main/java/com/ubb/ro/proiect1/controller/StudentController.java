package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.dto.user.StudentDTO;
import com.ubb.ro.proiect1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> queryStudents(Authentication authentication) {
        return ResponseEntity.ok(this.userService.studentList(authentication));
    }


}
