package io.github.ninobomba.utils.spring.api.failure;

import org.springframework.http.HttpStatus;

public final class ApiFailureStatusMapper {

    private ApiFailureStatusMapper() {
    }

    public static HttpStatus toHttpStatus(ApiFailureCode failureCode) {
        return failureCode != null ? failureCode.httpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}