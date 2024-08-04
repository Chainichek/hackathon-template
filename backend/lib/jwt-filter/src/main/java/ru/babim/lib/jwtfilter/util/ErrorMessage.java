package ru.babim.lib.jwtfilter.util;

import java.time.LocalDateTime;

public record ErrorMessage(LocalDateTime timestamp,
                           String title,
                           int status,
                           String detail,
                           String instance) {
}
