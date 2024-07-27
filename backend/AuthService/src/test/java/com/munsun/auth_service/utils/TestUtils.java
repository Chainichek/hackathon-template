package com.munsun.auth_service.utils;

import com.munsun.auth_service.dto.request.UserInfoDto;
import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.models.User;
import com.munsun.auth_service.models.enums.Role;

import java.util.UUID;

public class TestUtils {
    public static UserInfoDto getUserInfoDto_Munir() {
        return new UserInfoDto(
                "munsun",
                "qwerty",
                Role.ADMIN,
                "Munir",
                "Sunchalyaev",
                26,
                "msunchalyaev@gmail.com",
                "79873022923"
        );
    }

    public static User getUser_Munir() {
        UserInfoDto userInfoDto = getUserInfoDto_Munir();
        return User.builder()
                .name(userInfoDto.name())
                .email(userInfoDto.email())
                .phoneNumber(userInfoDto.phoneNumber())
                .lastname(userInfoDto.lastname())
                .account(Account.builder()
                        .login(userInfoDto.login())
                        .password(userInfoDto.password())
                        .role(Role.valueOf(userInfoDto.role().name()))
                        .build())
                .age(userInfoDto.age())
                .build();
    }

    public static User getUserPersistent_Munir() {
        User user = getUser_Munir();
            user.setUserId(UUID.randomUUID());
        return user;
    }
}
