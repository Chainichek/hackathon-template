package ru.chainichek.hackathon.template.activity.dto.activity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import ru.chainichek.hackathon.template.activity.annotation.IsDateAfterOtherDate;

import java.time.LocalDateTime;

import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.DESCRIPTION_MAX_LENGTH;
import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.TITLE_MAX_LENGTH;
import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.TITLE_MIN_LENGTH;

@IsDateAfterOtherDate(getStartAtMethodName = "startAt", getEndAtMethodName = "endAt")
public record ActivityRegistrationRequestDto(@Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH)
                                             String title,
                                             @Size(max = DESCRIPTION_MAX_LENGTH)
                                             String description,
                                             @Future
                                             LocalDateTime startAt,
                                             @Future
                                             LocalDateTime endAt
) {
}
