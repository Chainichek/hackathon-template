package com.munsun.employee_service.services.impl.clients;

import com.munsun.employee_service.dto.request.AccountDto;
import com.munsun.employee_service.dto.response.AccountPersistentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth")
public interface EmployeeFeignClient {
    @PostMapping("/api/v1/accounts/register")
    AccountPersistentDto getAccount(@RequestBody AccountDto accountDto);
}
