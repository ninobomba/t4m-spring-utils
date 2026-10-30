package io.github.ninobomba.utils.spring.web.pageable;

import lombok.Builder;

@Builder(toBuilder = true)
public record PageableRequest(
        int page,
        int offset,
        int size,
        String sortBy,
        String sortOrder
) {
    public PageableRequest {
        if (sortBy != null) {
            sortBy = sortBy.strip();
        }
        if (sortOrder != null) {
            sortOrder = sortOrder.strip();
        }
    }
}