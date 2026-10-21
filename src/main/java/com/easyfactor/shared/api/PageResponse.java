package com.easyfactor.shared.api;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * Pagination response independent of Spring Data types.
 */
public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages) {

    public static <T> PageResponse<T> from(Page<T> page) {
        // TODO
        return null;
    }
}
