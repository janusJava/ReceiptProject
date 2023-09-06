package com.alibou.security.project.baza.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }
    @GetMapping("/data")
    public ResponseEntity<User> getAccountByUsername(String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAccountByUsername(email));
    }
}
