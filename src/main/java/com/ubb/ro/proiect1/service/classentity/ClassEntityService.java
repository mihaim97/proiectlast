package com.ubb.ro.proiect1.service.classentity;


import com.ubb.ro.proiect1.dto.classentity.ClassEntityDTO;
import com.ubb.ro.proiect1.dto.classentity.ClassEntityDTO2;
import com.ubb.ro.proiect1.dto.classentity.ClassEntityDTOView;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClassEntityService {

    void addClass(ClassEntityDTO classEntity);

    void delete(int id);

    ClassEntityDTO findById(int id);

    List<ClassEntityDTOView> getClasses(Authentication authentication);

    List<ClassEntityDTO2> allClasses(Authentication authentication);
}
