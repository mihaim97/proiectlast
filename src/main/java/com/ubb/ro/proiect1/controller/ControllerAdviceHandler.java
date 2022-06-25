package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.security.util.exception.SessionGradeException;
import com.ubb.ro.proiect1.util.SimpleResponse;
import org.dom4j.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SessionGradeException.class})
    public final ResponseEntity<SimpleResponse> campaignHandler(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new SimpleResponse(ex.getMessage()));
    }

}
