package com.ubb.ro.proiect1.service.user;

import com.ubb.ro.proiect1.dto.user.*;
import com.ubb.ro.proiect1.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    User loadUser(String username);

    List<StudentDTO> studentList(Authentication authentication);

    List<UserDTO> userList(Authentication authentication);

    UserFullInfoDTO userInfo(Authentication authentication, int id);

    void addOuUpdateUser(Authentication authentication, NewUserDTO newUserDTO);

    void changeUserPassword(Authentication authentication, int userId, String password);

    void addUserRole(Authentication authentication, NewRole newRole);

    void deleteRole(Authentication authentication, int userId, int roleId);

    List<UserRoleDTO> queryAllRole(Authentication authentication, int userId);

}
