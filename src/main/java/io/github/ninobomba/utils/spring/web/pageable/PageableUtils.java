package io.github.ninobomba.utils.spring.web.pageable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public final class PageableUtils {

    private PageableUtils(){
    }

    public static Pageable buildPageable(int offset, int size, String sortBy, String sortOrder) {
        int pageSize = Math.clamp(size, 1, 100); // cap
        Sort.Direction dir = parseDirection(sortOrder);
        return new OffsetBasedPageRequest(offset, pageSize, Sort.by(new Sort.Order(dir, safeSortBy(sortBy))));
    }

    public static Sort.Direction parseDirection(String sortOrder) {
        try {
            return Sort.Direction.fromString(sortOrder);
        } catch (Exception e) {
            log.warn("Domain: invalid sortOrder='{}', defaulting to ASC", sortOrder);
            return Sort.Direction.ASC;
        }
    }

    public static String safeSortBy(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) return "id";
        return switch (sortBy.trim()) {
            case "createdAt", "createdDate", "lastModifiedAt", "id" -> sortBy.trim();
            default -> "id";
        };
    }

}