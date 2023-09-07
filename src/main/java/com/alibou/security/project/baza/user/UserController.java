package com.alibou.security.project.baza.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }
    @GetMapping("/data")
    public ResponseEntity<User> getUserByUsername(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserByUsername(name));
    }

    @GetMapping("/logged")
    public User getLoggedUserData() {
        log.trace("Account Controller: Getting details about logged user");
        User result = userService.getLoggedUserData();
        log.debug("Account Controller: Got details about logged user {result: {}}", result);
        return result;
    }

    @PutMapping(value = "/{userId}/password", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void changePasswordAccount(@NotNull Integer userId, @NotNull @Valid UserUpdatePasswordRequest request) {
        log.trace("Account Controller: Changing password {accountId: {}, request: {}}", userId, request);
        userService.changePassword(userId, request);
        log.debug("Account Controller: Changed password {accountId: {}", userId);
    }

}
