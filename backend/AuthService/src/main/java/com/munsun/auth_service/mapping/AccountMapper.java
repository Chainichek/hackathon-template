package com.munsun.auth_service.mapping;

import com.munsun.auth_service.dto.request.AccountDto;
import com.munsun.auth_service.dto.response.AccountPersistentDto;
import com.munsun.auth_service.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account map(AccountDto accountDto);
    AccountPersistentDto map(Account account);
}
