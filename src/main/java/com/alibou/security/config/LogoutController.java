package com.alibou.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/logout")
@Slf4j
public class LogoutController {

    private final LogoutService logoutService;
    @Autowired
    public LogoutController(LogoutService logoutService){
        this.logoutService = logoutService;
    }

    @GetMapping
    public ResponseEntity<?> logout() {
        log.trace("Logout Controller: Logout user");
        ResponseCookie cookie = ResponseCookie.from("access_token", "1").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("LOGOUT");
    }

}
