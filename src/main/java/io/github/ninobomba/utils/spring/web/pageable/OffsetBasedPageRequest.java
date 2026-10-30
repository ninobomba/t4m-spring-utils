package io.github.ninobomba.utils.spring.web.pageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

final class OffsetBasedPageRequest implements Pageable {

    private final long offset;
    private final int pageSize;
    private final Sort sort;

    OffsetBasedPageRequest(long offset, int pageSize, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset must not be negative");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }
        this.offset = offset;
        this.pageSize = pageSize;
        this.sort = Objects.requireNonNull(sort, "Sort must not be null");
    }

    @Override
    public int getPageNumber() {
        return Math.toIntExact(offset / pageSize);
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(Math.addExact(offset, pageSize), pageSize, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious()
                ? new OffsetBasedPageRequest(Math.max(0, offset - pageSize), pageSize, sort)
                : first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, pageSize, sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page index must not be negative");
        }
        return new OffsetBasedPageRequest(Math.multiplyExact((long) pageNumber, pageSize), pageSize, sort);
    }

    @Override
    public boolean hasPrevious() {
        return offset > 0;
    }
}