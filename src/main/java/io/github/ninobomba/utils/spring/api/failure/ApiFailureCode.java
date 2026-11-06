package io.github.ninobomba.utils.spring.api.failure;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Locale;

public enum ApiFailureCode {
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "Request validation failed."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Authentication is required."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Access is denied."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "The requested resource was not found."),
    CONFLICT(HttpStatus.CONFLICT, "The request conflicts with existing data."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded. Please try again later."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Service is temporarily unavailable."),
    REQUEST_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "The request could not be completed.");

    private final HttpStatus httpStatus;
    private final String clientMessage;

    ApiFailureCode(HttpStatus httpStatus, String clientMessage) {
        this.httpStatus = httpStatus;
        this.clientMessage = clientMessage;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }

    public String clientMessage() {
        return clientMessage;
    }

    public static ApiFailureCode fromCode(String code) {
        if (code == null || code.isBlank()) {
            return REQUEST_FAILED;
        }

        String normalized = code.trim().toUpperCase(Locale.ROOT);
        return Arrays.stream(values())
                .filter(failureCode -> normalized.equals(failureCode.name()) || normalized.startsWith(failureCode.name()))
                .findFirst()
                .orElse(REQUEST_FAILED);
    }
}