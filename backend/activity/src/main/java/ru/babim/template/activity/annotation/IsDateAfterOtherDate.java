package ru.babim.template.activity.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.babim.template.activity.validation.validator.DateAfterOtherDateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.babim.template.activity.validation.message.ActivityValidationMessage.DATE_AFTER_OTHER_DATE_MESSAGE;


@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateAfterOtherDateValidator.class)
public @interface IsDateAfterOtherDate {
    String message() default DATE_AFTER_OTHER_DATE_MESSAGE;
    String getStartAtMethodName();
    String getEndAtMethodName();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
