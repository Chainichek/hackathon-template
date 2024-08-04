package ru.babim.template.employee.queries.producers;

import ru.babim.template.employee.queries.payload.EmployeeCreationMessage;

public interface EmployeeProducer {
    void sendSuccessCreateEmployee(EmployeeCreationMessage employeeCreationMessage);
}
