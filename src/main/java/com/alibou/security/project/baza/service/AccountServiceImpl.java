package com.alibou.security.project.baza.service;

import com.alibou.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository accountRepository;

    @Autowired
    private AccountServiceImpl(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

}
