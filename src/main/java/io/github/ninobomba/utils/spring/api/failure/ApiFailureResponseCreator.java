package io.github.ninobomba.utils.spring.api.failure;

import io.github.ninobomba.utils.spring.api.response.record.ApiRecordResponse;
import io.github.ninobomba.utils.spring.api.response.record.ApiResponseCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

@Slf4j
public final class ApiFailureResponseCreator {

    private ApiFailureResponseCreator() {
    }

    public static ResponseEntity<ApiRecordResponse> create(String requestId, ApiFailureCode failureCode) {
        return create(requestId, failureCode, null);
    }

    public static ResponseEntity<ApiRecordResponse> create(String requestId, ApiFailureCode failureCode, String failureMessage) {
        String clientMessage = (failureMessage != null && !failureMessage.isBlank())
                ? failureCode.clientMessage() + ": " + failureMessage.trim()
                : failureCode.clientMessage();

        return ApiResponseCreator.createFailureResponse(
                requestId,
                clientMessage,
                ApiFailureStatusMapper.toHttpStatus(failureCode)
        );
    }

    public static ResponseEntity<ApiRecordResponse> create(String requestId, String failureCode) {
        if (failureCode == null || failureCode.isBlank()) {
            return create(requestId, ApiFailureCode.REQUEST_FAILED, null);
        }

        String code;
        String message = null;
        if (failureCode.contains(":")) {
            String[] parts = failureCode.split(":", 2);
            code = parts[0].trim();
            message = parts.length > 1 && !parts[1].isBlank() ? parts[1].trim() : null;
        } else {
            code = failureCode.trim();
        }

        var parsedCode = ApiFailureCode.fromCode(code);
        if (parsedCode == ApiFailureCode.REQUEST_FAILED && !isRecognizedRequestFailed(failureCode)) {
            log.error("Unknown failure code [{}] for requestId={}", failureCode, requestId);
        }
        return create(requestId, parsedCode, message);
    }

    private static boolean isRecognizedRequestFailed(String failureCode) {
        return failureCode != null
                && failureCode.trim().toUpperCase(Locale.ROOT).startsWith(ApiFailureCode.REQUEST_FAILED.name());
    }
}