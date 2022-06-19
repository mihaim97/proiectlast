package com.ubb.ro.proiect1.service.user;

import com.ubb.ro.proiect1.dao.UserDAO;
import com.ubb.ro.proiect1.dto.user.*;
import com.ubb.ro.proiect1.entity.Role;
import com.ubb.ro.proiect1.entity.User;
import com.ubb.ro.proiect1.util.SingleResultFromList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional
    public List<UserDTO> userList(Authentication authentication) {
        return this.userDAO.queryUser().stream().map(this::userList).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserFullInfoDTO userInfo(Authentication authentication, int id) {
        return this.userFullInfo(this.userDAO.searchById(id));
    }

    @Override
    @Transactional
    public void addOuUpdateUser(Authentication authentication, NewUserDTO newUserDTO) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByIdList(newUserDTO.getId()));
        if(user == null) {
            this.newUser(newUserDTO);
        }else {
            this.updateUser(user, newUserDTO);
        }
    }

    @Override
    @Transactional
    public void changeUserPassword(Authentication authentication, int userId, String password) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByIdList(userId));
        if(user != null) {
            user.setPassword(passwordEncoder.encode(password));
        }
    }

    @Override
    @Transactional
    public void addUserRole(Authentication authentication, NewRole newRole) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByIdList(newRole.getUserId()));
        if(user != null) {
            if(!user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()).contains(newRole.getRole())) {
                Role role = new Role();
                role.setRole(newRole.getRole());
                role.setUserId(user);
                user.getRoles().add(role);
            }
        }
    }

    @Override
    @Transactional
    public void deleteRole(Authentication authentication, int userId, int roleId) {
        User user = SingleResultFromList.getSingleResult(this.userDAO.searchByIdList(userId));
    }

    private UserDTO userList(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setProgram(user.getProgram());
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setYear(user.getYear());
        userDto.setId(user.getId());
        return userDto;
    }

    private UserFullInfoDTO userFullInfo(User user) {
        UserFullInfoDTO userDto = new UserFullInfoDTO();
        userDto.setEmail(user.getEmail());
        userDto.setProgram(user.getProgram());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setYear(user.getYear());
        userDto.setId(user.getId());
        userDto.setEnabled(user.isEnabled());
        return userDto;
    }

    private void newUser(NewUserDTO newUserDTO) {
        this.validateUsername(newUserDTO.getUsername());
        User user = new User();
        user.setProgram(newUserDTO.getProgram());
        user.setYear(newUserDTO.getYear());
        user.setEmail(newUserDTO.getEmail());
        user.setLastName(newUserDTO.getLastName());
        user.setFirstName(newUserDTO.getFirstName());
        user.setUsername(newUserDTO.getUsername());
        user.setEnabled(newUserDTO.isEnabled());
        System.out.println(newUserDTO.getPassword());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        this.userDAO.persist(user);
    }

    private void updateUser(User user, NewUserDTO newUserDTO) {
        user.setYear(newUserDTO.getYear());
        user.setEmail(newUserDTO.getEmail());
        user.setPassword(newUserDTO.getProgram());
        user.setLastName(newUserDTO.getLastName());
        user.setFirstName(newUserDTO.getFirstName());
        user.setEnabled(newUserDTO.isEnabled());
    }

    private void validateUsername(String username) {
        if(this.userDAO.searchByName(username).size() > 0) {
            throw new RuntimeException("Username is taken");
        }
    }

    private StudentDTO toStudent(User user) {
        StudentDTO dto = new StudentDTO();
        dto.setId(user.getId());
        dto.setName(user.getFirstName() + " " + user.getLastName());
        dto.setProgram(user.getProgram());
        dto.setYear(user.getYear());
        return dto;
    }
}
