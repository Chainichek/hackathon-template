package com.munsun.auth_service.services.impl;

import com.munsun.auth_service.dto.request.LoginPasswordDto;
import com.munsun.auth_service.dto.request.UserInfoDto;
import com.munsun.auth_service.dto.response.JwtTokenDto;
import com.munsun.auth_service.exceptions.SaveUserException;
import com.munsun.auth_service.exceptions.UserNotFoundException;
import com.munsun.auth_service.mapping.UserMapper;
import com.munsun.auth_service.models.User;
import com.munsun.auth_service.repositories.UserRepository;
import com.munsun.auth_service.services.AuthService;
import com.munsun.auth_service.services.impl.providers.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @Override
    public JwtTokenDto auth(LoginPasswordDto loginPassword) {
        Optional<User> user = userRepository.findByAccount_Login(loginPassword.login());
        if(user.isEmpty()) {
            throw new UserNotFoundException("Invalid login or password");
        }
        return new JwtTokenDto(jwtProvider.generate(user.get()));
    }

    @Override
    public void register(UserInfoDto userInfo) {
        if(userRepository.existsByEmailOrAccount_LoginOrPhoneNumber(userInfo.email(), userInfo.login(), userInfo.phoneNumber())) {
            throw new SaveUserException("User has already been created");
        }
        User user = userMapper.map(userInfo);
        userRepository.save(user);
    }
}
