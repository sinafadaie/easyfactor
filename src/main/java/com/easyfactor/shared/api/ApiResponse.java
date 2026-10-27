package com.easyfactor.shared.api;

import java.time.Instant;

/**
  Uniform success/error envelope for every API response.
  Imagine the frontend (the Telegram Mini App) calls 20 different endpoints.
  Without a uniform envelope, each endpoint returns a different shape — one returns a product, another
  returns a list, errors return yet another format.
  The frontend needs custom parsing logic for each.
  With the envelope, every response — success or failure — looks the same:

  "check success. If true, read data. If false, read error.code and show a message."                                                                                                                                                        copied 475 chars to clipboard
 */
public record ApiResponse<T>(
        boolean success,
        T data,
        ApiError error,
        Instant timestamp) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, Instant.now());
    }

    public static <T> ApiResponse<T> error(ApiError error) {
        return new ApiResponse<>(false, null, error, Instant.now());
    }
}
