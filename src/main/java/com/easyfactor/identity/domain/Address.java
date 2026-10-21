package com.easyfactor.identity.domain;

import java.util.UUID;

/**
 * Customer address value object (part of the Customer aggregate).
 */
public record Address(
        UUID id,
        String label,
        String line1,
        String line2,
        String city,
        String state,
        String postalCode,
        String country) {
}
