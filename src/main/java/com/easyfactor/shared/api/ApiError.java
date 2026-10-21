package com.easyfactor.shared.api;

import java.util.Map;

/**
 * Machine-readable error payload.
 */
public record ApiError(
        String code,
        String message,
        Map<String, String> fieldErrors,
        String traceId) {
}
