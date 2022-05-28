package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.dto.session.SessionDTO;
import com.ubb.ro.proiect1.dto.session.SessionDTOView;
import com.ubb.ro.proiect1.service.session.SessionService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("session/new")
    public ResponseEntity<String> newSession(Authentication authentication, @RequestBody SessionDTO sessionDTO) {
        this.sessionService.addNewSession(sessionDTO);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

}
