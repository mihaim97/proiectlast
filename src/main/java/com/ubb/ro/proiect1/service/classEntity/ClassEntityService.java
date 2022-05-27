package com.ubb.ro.proiect1.service.classEntity;


import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClassEntityService {

    void addClass(ClassEntityDTO classEntity);

    void delete(int id);

    ClassEntityDTO findById(int id);

    List<ClassEntityDTOView> getClasses(Authentication authentication);
}
