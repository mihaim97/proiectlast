package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.dto.classentity.ClassEntityDTO;
import com.ubb.ro.proiect1.dto.classentity.ClassEntityDTO2;
import com.ubb.ro.proiect1.dto.classentity.ClassEntityDTOView;
import com.ubb.ro.proiect1.service.classentity.ClassEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassEntityController {

    @Autowired
    private ClassEntityService classEntityService;

    @GetMapping("/classes")
    public ResponseEntity<List<ClassEntityDTOView>> getAll(Authentication authentication){
        return ResponseEntity.ok(this.classEntityService.getClasses(authentication));
    }

    @GetMapping("/classes/all")
    public ResponseEntity<List<ClassEntityDTO2>> allClasses(Authentication authentication){
        return ResponseEntity.ok(this.classEntityService.allClasses(authentication));
    }

    @PostMapping(value = "/addclass", consumes = "application/json")
    public void addClass(@RequestBody ClassEntityDTO classEntityDTO){
        classEntityService.addClass(classEntityDTO);
    }

    @GetMapping(value = "teacherClasses")
    public ResponseEntity<List<ClassEntityDTO2>> teacherClass(Authentication authentication) {
        return ResponseEntity.ok(this.classEntityService.allTeacherClass(authentication));
    }
}
