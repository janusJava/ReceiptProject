package com.alibou.security.project.baza.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getAccountByUsername(String email) {
        log.trace("Service: Getting account by username {username: {}}", email);
        User result = userRepository.findByEmail(email).get();
        log.debug("Service: Got account by username {username: {}, result: {}}", email, result);
        return result;
    }

}
