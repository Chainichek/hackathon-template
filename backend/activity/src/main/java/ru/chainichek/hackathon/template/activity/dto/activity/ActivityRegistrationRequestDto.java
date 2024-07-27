package ru.chainichek.hackathon.template.activity.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import ru.chainichek.hackathon.template.activity.annotation.IsDateAfterOtherDate;
import ru.chainichek.hackathon.template.activity.validation.value.ValidationValue;

import java.time.LocalDateTime;

import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.DESCRIPTION_MAX_LENGTH;
import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.TITLE_MAX_LENGTH;
import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.TITLE_MIN_LENGTH;

@IsDateAfterOtherDate(getStartAtMethodName = "startAt", getEndAtMethodName = "endAt")
public record ActivityRegistrationRequestDto(@Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH)
                                             String title,
                                             @Size(max = DESCRIPTION_MAX_LENGTH)
                                             String description,
                                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ValidationValue.DATE_TIME_FORMAT_PATTERN)
                                             @Future
                                             LocalDateTime startAt,
                                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ValidationValue.DATE_TIME_FORMAT_PATTERN)
                                             @Future
                                             LocalDateTime endedAt
) {
}
