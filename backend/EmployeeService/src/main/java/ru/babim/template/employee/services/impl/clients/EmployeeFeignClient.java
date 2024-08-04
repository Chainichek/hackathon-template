package ru.babim.template.employee.services.impl.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.babim.template.employee.dto.request.AccountDto;
import ru.babim.template.employee.dto.response.AccountPersistentDto;

@FeignClient(value = "auth-mc", url = "${client.auth.url}")
public interface EmployeeFeignClient {
    @PostMapping("/api/v1/accounts/register")
    AccountPersistentDto getAccount(@RequestBody AccountDto accountDto);
}
