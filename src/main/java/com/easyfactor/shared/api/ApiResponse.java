package com.easyfactor.shared.api;

import java.time.Instant;

/**
 * Uniform success/error envelope for every API response.
 */
public record ApiResponse<T>(
        boolean success,
        T data,
        ApiError error,
        Instant timestamp) {

    public static <T> ApiResponse<T> ok(T data) {
        // TODO
        return null;
    }

    public static <T> ApiResponse<T> error(ApiError error) {
        // TODO
        return null;
    }
}
