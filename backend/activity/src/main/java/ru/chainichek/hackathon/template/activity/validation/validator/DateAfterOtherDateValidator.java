package ru.chainichek.hackathon.template.activity.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.chainichek.hackathon.template.activity.annotation.IsDateAfterOtherDate;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Slf4j
public class DateAfterOtherDateValidator implements ConstraintValidator<IsDateAfterOtherDate, Object> {
    private String getStartAtMethodName;
    private String getEndAtMethodName;

    @Override
    public void initialize(IsDateAfterOtherDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.getStartAtMethodName = constraintAnnotation.getStartAtMethodName();
        this.getEndAtMethodName = constraintAnnotation.getEndAtMethodName();
    }

    @Override
    @SneakyThrows
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            return true;
        }

        final Method startMethod = o.getClass().getMethod(this.getStartAtMethodName);
        final Method endMethod = o.getClass().getMethod(this.getEndAtMethodName);

        final Temporal startAtTemporal = (Temporal) startMethod.invoke(o);
        final Temporal endAtTemporal = (Temporal) endMethod.invoke(o);

        log.debug("Validating dates: startAt = {}, endAt = {}", startAtTemporal, endAtTemporal);

        if (startAtTemporal == null || endAtTemporal == null) {
            return true;
        }

        final LocalDateTime startAtDateTime = startAtTemporal instanceof LocalDate ? ((LocalDate) startAtTemporal).atStartOfDay() : (LocalDateTime) startAtTemporal;
        final LocalDateTime endAtDateTime = endAtTemporal instanceof LocalDate ? ((LocalDate) endAtTemporal).atStartOfDay() : (LocalDateTime) endAtTemporal;


        return startAtDateTime.isBefore(endAtDateTime);
    }

}
