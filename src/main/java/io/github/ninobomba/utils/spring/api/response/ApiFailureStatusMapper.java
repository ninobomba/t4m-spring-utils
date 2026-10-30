package io.github.ninobomba.utils.spring.api.response;

import org.springframework.http.HttpStatus;

import java.util.Locale;

public final class ApiFailureStatusMapper {

    private ApiFailureStatusMapper() {
    }

    public static HttpStatus toStatus(String failureMessage) {
        if (failureMessage == null || failureMessage.isBlank()) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        String message = failureMessage.toLowerCase(Locale.ROOT);
        if (message.contains("invalid credentials") || message.contains("login request failed")
                || message.contains("verify your credentials")) {
            return HttpStatus.UNAUTHORIZED;
        }
        if (message.contains("not found") || message.contains("does not exist")) {
            return HttpStatus.NOT_FOUND;
        }
        if (message.contains("already exists") || message.contains("maximum number")
                || message.contains("limit reached")) {
            return HttpStatus.CONFLICT;
        }
        if (message.contains("validation") || message.contains("invalid")
                || message.contains("required") || message.contains("must be")) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}