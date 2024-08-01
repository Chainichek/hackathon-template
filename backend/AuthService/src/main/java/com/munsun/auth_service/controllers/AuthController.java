package com.munsun.auth_service.controllers;

import com.munsun.auth_service.dto.request.AccountDto;
import com.munsun.auth_service.dto.response.AccountPersistentDto;
import com.munsun.auth_service.dto.response.JwtTokenDto;
import com.munsun.auth_service.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {
    private final AuthService service;
    @PostMapping("/login")
    public JwtTokenDto loginUser(@RequestBody @Valid AccountDto accountDto) {
        return service.auth(accountDto);
    }

    @PostMapping("/accounts/register")
    public AccountPersistentDto registerNewAccount(@RequestBody @Valid AccountDto accountDto) {
        return service.register(accountDto);
    }
}
