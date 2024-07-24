package com.munsun.auth_service.mapping;

import com.munsun.auth_service.dto.request.UserInfoDto;
import com.munsun.auth_service.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "account.login", source = "login")
    @Mapping(target = "account.password", source = "password")
    @Mapping(target = "account.role", source = "role")
    User map(UserInfoDto userInfo);
}
