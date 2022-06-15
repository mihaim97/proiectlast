package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.service.classEntity.ClassEntityDTO;
import com.ubb.ro.proiect1.service.classEntity.ClassEntityDTOView;
import com.ubb.ro.proiect1.service.classEntity.ClassEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassEntity {

    @Autowired
    private ClassEntityService classEntityService;


    @GetMapping("/classes")
    public ResponseEntity<List<ClassEntityDTOView>> getAll(Authentication authentication){
        return ResponseEntity.ok(this.classEntityService.getClasses(authentication));
    }

    @GetMapping(value = "/classes/percentage/{id}")
    public ResponseEntity<List> getPercentage(@PathVariable("id") int id, Authentication authentication){
        return ResponseEntity.ok(this.classEntityService.getClassesPercentage(id, authentication));
    }

    @PostMapping(value = "/addclass", consumes = "application/json")
    public void addClass(@RequestBody ClassEntityDTO classEntityDTO){
        classEntityService.addClass(classEntityDTO);
    }
}
