package io.github.ninobomba.utils.spring.api.response.record;


import io.github.ninobomba.utils.java.constants.processes.BizProcessResponse;
import org.springframework.http.HttpStatus;

public interface IMappingResponse {

    static HttpStatus mapStatus(BizProcessResponse.Status status) {

        return switch (status) {
            case CREATED -> HttpStatus.CREATED;
            case PROCESSED -> HttpStatus.OK;
            case INVALID -> HttpStatus.BAD_REQUEST;
            case CONFLICTED -> HttpStatus.CONFLICT;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

    }

}
