package com.alibou.security.project.baza.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String name) {
        log.trace("Service: Getting account by username {username: {}}", name);
        User result = userRepository.findByFirstName(name).get();
        log.debug("Service: Got account by username {username: {}, result: {}}", name, result);
        return result;
    }

    @Override
    public User getLoggedUserData(){
        //TODO: Replace Mocked Username
        String userName = "Admin";
        return getUserByUsername(userName);
    }

    @Override
    public void changePassword(@NotNull Integer userId, @NotNull @Valid UserUpdatePasswordRequest request) {
        log.trace("Service: Changing account password {accountId: {}, request: {}}", userId, request);
        User user = userRepository.findById(userId).get();
        validatePassword(user.getPassword(), request);
        String newPasswordHash = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(newPasswordHash);
        userRepository.save(user);
        log.debug("Service: Changed account password {accountId: {}}", user);
    }

    private boolean validatePassword(String passwordHash, UserUpdatePasswordRequest request) {
        log.trace("Service: Checking validate account password");
        if(passwordHash.isEmpty()) {
            log.error("Password has null value");
            throw new RuntimeException("Password has null value");
        }
        boolean status = passwordEncoder.matches(request.getOldPassword(), passwordHash);
        if(!status) {
            log.error("Given password does not match");
            throw new RuntimeException("Given password does not match");
        }
        log.debug("Service: Password validated");
        return status;
    }

}
