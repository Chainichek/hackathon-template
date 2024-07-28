package com.munsun.auth_service.services.impl;

import com.munsun.auth_service.dto.request.AccountDto;
import com.munsun.auth_service.dto.response.AccountPersistentDto;
import com.munsun.auth_service.dto.response.JwtTokenDto;
import com.munsun.auth_service.exceptions.AccountNotFoundException;
import com.munsun.auth_service.exceptions.SaveAccountException;
import com.munsun.auth_service.mapping.AccountMapper;
import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.models.enums.Role;
import com.munsun.auth_service.repositories.AccountRepository;
import com.munsun.auth_service.services.AuthService;
import com.munsun.auth_service.services.impl.providers.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
    private final AccountRepository accountResository;
    private final JwtProvider jwtProvider;
    private final AccountMapper accountMapper;

    @Override
    public JwtTokenDto auth(AccountDto loginPassword) {
        Optional<Account> user = accountResository.findAccountByLogin(loginPassword.login());
        if(user.isEmpty()) {
            throw new AccountNotFoundException("Invalid login or password");
        }
        return jwtProvider.getJwtTokenDto(user.get());
    }

    @Override
    public AccountPersistentDto register(AccountDto accountDto) {
        if(accountResository.existsByLogin(accountDto.login())) {
            throw new SaveAccountException(String.format("Account with login=%s is already exists",
                    accountDto.login()));
        }
        Account account = accountMapper.map(accountDto);
            account.setRole(Role.EMPLOYEE);
        accountResository.save(account);
        return accountMapper.map(account);
    }
}