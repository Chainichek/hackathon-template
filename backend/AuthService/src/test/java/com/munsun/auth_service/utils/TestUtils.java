package com.munsun.auth_service.utils;

import com.munsun.auth_service.dto.request.AccountDto;
import com.munsun.auth_service.dto.response.AccountPersistentDto;
import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.models.enums.Role;

import java.util.UUID;

public class TestUtils {

    public static AccountDto getAccountDto_Munir() {
        return new AccountDto(
                "munsun",
                "qwerty12345"
        );
    }

    public static Account getAccountTransient_Munir() {
        AccountDto accountDto = getAccountDto_Munir();
        return Account.builder()
                .login(accountDto.login())
                .password(accountDto.password())
                .role(Role.ADMIN)
                .build();
    }

    public static Account getAccountPersistent_Munir() {
        AccountDto accountDto = getAccountDto_Munir();
        return Account.builder()
                .accountId(UUID.randomUUID())
                .login(accountDto.login())
                .password(accountDto.password())
                .role(Role.ADMIN)
                .build();
    }

    public static AccountPersistentDto getAccountPersistentDto_Munir(UUID accountId) {
        Account account = getAccountPersistent_Munir();
        return new AccountPersistentDto(
                accountId,
                account.getLogin(),
                account.getPassword(),
                account.getRole().name()
        );
    }
}
