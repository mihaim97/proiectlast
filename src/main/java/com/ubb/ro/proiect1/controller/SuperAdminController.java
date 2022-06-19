package com.ubb.ro.proiect1.controller;

import com.ubb.ro.proiect1.dto.user.*;
import com.ubb.ro.proiect1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SuperAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("new")
    public ResponseEntity<String> addUser(Authentication authentication, @RequestBody NewUserDTO newUserDTO) {
        this.userService.addOuUpdateUser(authentication, newUserDTO);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @GetMapping("list")
    public ResponseEntity<List<UserDTO>> usersList(Authentication authentication) {
        return ResponseEntity.ok(this.userService.userList(authentication));
    }

    @GetMapping("info")
    public ResponseEntity<UserFullInfoDTO> userInfo(Authentication authentication, @RequestParam(name = "userId") int id) {
        return ResponseEntity.ok(this.userService.userInfo(authentication, id));
    }

    @PatchMapping("changePassword")
    public ResponseEntity<String> changePassword(Authentication authentication, @RequestBody ChangePassword changePassword) {
        this.userService.changeUserPassword(authentication, changePassword.getUserId(), changePassword.getPassword());
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @PostMapping("add/role")
    public ResponseEntity<String> addRole(Authentication authentication, @RequestBody NewRole role) {
        this.userService.addUserRole(authentication, role);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }

    @GetMapping("role/list")
    public ResponseEntity<List<UserRoleDTO>> userRoles(Authentication authentication,
                                            @RequestParam("userId") int userId) {
        return ResponseEntity.ok(this.userService.queryAllRole(authentication, userId));
    }

    @DeleteMapping("delete/role")
    public ResponseEntity<String> deleteRole(Authentication authentication,
                                             @RequestParam("userId") int userId,
                                             @RequestParam("roleId") int roleId) {
        this.userService.deleteRole(authentication, userId, roleId);
        return ResponseEntity.ok("{\"response\":\"ok\"}");
    }


}
