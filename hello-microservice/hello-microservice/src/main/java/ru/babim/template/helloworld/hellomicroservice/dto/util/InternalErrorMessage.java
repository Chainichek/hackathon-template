package ru.babim.template.helloworld.hellomicroservice.dto.util;

import java.time.LocalDateTime;

public record InternalErrorMessage(LocalDateTime timestamp,
                                   String title,
                                   int status,
                                   String detail,
                                   String[] stackTrace,
                                   String instance) {

}
