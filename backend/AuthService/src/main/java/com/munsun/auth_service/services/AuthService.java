package com.munsun.auth_service.services;

import com.munsun.auth_service.dto.request.AccountDto;
import com.munsun.auth_service.dto.response.AccountPersistentDto;
import com.munsun.auth_service.dto.response.JwtTokenDto;

public interface AuthService {
    JwtTokenDto auth(AccountDto loginPassword);

    AccountPersistentDto register(AccountDto accountDto);
}
