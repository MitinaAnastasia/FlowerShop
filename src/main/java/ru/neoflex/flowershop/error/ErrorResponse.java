package ru.neoflex.flowershop.error;

import java.time.LocalDateTime;

public record ErrorResponse (LocalDateTime localDateTime, int status, String error, String message) {
}
