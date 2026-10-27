package com.easyfactor.shared.api;

import java.util.List;

import org.springframework.data.domain.Page;

/**
  Pagination response independent of Spring Data types.

   we need pagination for products (for  single owner)
   So the frontend asks: "give me products, page 2, 20 per page."
   "How many products do I have in total?"/How many pages are there?"/"Which page am I on?" → 2
 -----------------
 Spring Data bundles all of this together in one object called Page<Product>.
 Spring's Page is a big framework object with many internal fields
 (sorting info, Spring-specific classes...). If we sent it directly to the frontend, the JSON would be full of
 that internal noise, and its shape could change when Spring upgrades.
 ----------------
 One small note: page numbers in Spring start at 0 (page 0, 1, 2...).
 We pass it through as-is — the frontend can add 1 for display.
 */
public record PageResponse<T>(
        List<T> content,        // the actual list
        int page,
        int size,               // how many per page
        long totalElements,
        int totalPages) {

    public static <T> PageResponse<T> from(Page<T> page) {  //map spring Page to our PageResponse
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}
