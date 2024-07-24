package com.munsun.auth_service.services;

import com.munsun.auth_service.dto.request.LoginPasswordDto;
import com.munsun.auth_service.dto.request.UserInfoDto;
import com.munsun.auth_service.dto.response.JwtTokenDto;

public interface AuthService {
    JwtTokenDto auth(LoginPasswordDto loginPassword);
    void register(UserInfoDto userInfo);
}
