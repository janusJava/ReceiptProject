package com.alibou.security.project.baza.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {

    User getUserByUsername(String name);

    User getLoggedUserData();

    void changePassword(@NotNull Integer userId, @NotNull @Valid UserUpdatePasswordRequest request);
}
