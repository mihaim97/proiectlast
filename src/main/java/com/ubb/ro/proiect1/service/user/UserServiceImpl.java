package com.ubb.ro.proiect1.service.user;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.dto.user.StudentDTO;
import com.ubb.ro.proiect1.entity.Role;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User loadUser(String username) {
        return SingleResultFromList.getSingleResult(this.userDAO.searchByName(username));
    }

    @Override
    @Transactional
    public List<StudentDTO> studentList(Authentication authentication) {
        return this.userDAO.queryUser().stream().filter(el->
            el.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()).contains("ROLE_STUDENT")
        ).map(this::toStudent).collect(Collectors.toList());
    }

    private StudentDTO toStudent(User user) {
        StudentDTO dto = new StudentDTO();
        dto.setId(user.getId());
        dto.setName(user.getFullName());
        dto.setProgram(user.getProgram());
        dto.setYear(user.getYear());
        return dto;
    }
}
