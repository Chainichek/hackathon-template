package ru.babim.lib.jwtprovider.util;

import java.time.LocalDateTime;

public record ErrorMessage(LocalDateTime timestamp,
                           String title,
                           int status,
                           String detail,
                           String instance) {
}
