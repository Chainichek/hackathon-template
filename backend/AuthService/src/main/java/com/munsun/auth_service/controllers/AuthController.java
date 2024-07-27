package com.munsun.auth_service.controllers;

import com.munsun.auth_service.dto.request.LoginPasswordDto;
import com.munsun.auth_service.dto.request.UserInfoDto;
import com.munsun.auth_service.dto.response.JwtTokenDto;
import com.munsun.auth_service.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AuthController {
    private final AuthService service;
    @PostMapping("/login")
    public JwtTokenDto loginUser(@RequestBody @Valid LoginPasswordDto loginPassword) {
        return service.auth(loginPassword);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody @Valid UserInfoDto userInfo) {
        service.register(userInfo);
    }
}
