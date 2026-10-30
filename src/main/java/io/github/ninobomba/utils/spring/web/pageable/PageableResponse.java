package io.github.ninobomba.utils.spring.web.pageable;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record PageableResponse(
        List<?> items,
        int page,
        int offset,
        int size,
        int totalElements,
        int totalPages
) {
}