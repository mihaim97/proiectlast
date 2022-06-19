package com.ubb.ro.proiect1.service.user;

import com.ubb.ro.proiect1.dto.user.StudentDTO;
import com.ubb.ro.proiect1.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    User loadUser(String username);

    List<StudentDTO> studentList(Authentication authentication);

}
