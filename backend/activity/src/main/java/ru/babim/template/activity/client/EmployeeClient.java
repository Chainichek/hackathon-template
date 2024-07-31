package ru.babim.template.activity.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.babim.template.activity.config.FeignConfig;
import ru.babim.template.activity.dto.employee.EmployeeDto;
import ru.babim.template.activity.dto.employee.GroupDto;

import java.util.UUID;

@FeignClient(name = "employee",
        url = "${app.client.employee.url}",
        path = "${app.client.employee.path.base-path}",
        configuration = FeignConfig.class)
public interface EmployeeClient {
    @RequestMapping(method = RequestMethod.GET, path = "${app.client.employee.path.find-employee}")
    EmployeeDto findEmployee(@RequestParam("login") String login);

    @RequestMapping(method = RequestMethod.GET, path = "${app.client.employee.path.find-group}")
    GroupDto findGroup(@PathVariable("groupId")UUID groupId);
}