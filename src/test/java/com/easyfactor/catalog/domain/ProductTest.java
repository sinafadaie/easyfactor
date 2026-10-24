package com.easyfactor.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import org.junit.jupiter.api.Test;

class ProductTest {

    private static final UUID OWNER_ID = UUID.randomUUID();
    private static final Instant NOW = Instant.parse("2026-10-21T10:00:00Z");

    private static ProductDraft draft(boolean active) {
        return new ProductDraft("Nike Air", "Running shoe", "shoe", "pair",
                Map.of("size", 42, "color", "black"), 85_000_000L,
                DigitalDeliveryType.NONE, null, "https://img.example/shoe.jpg", active);
    }

    @Test
    void createBuildsActiveProduct() {
        Product product = Product.create(OWNER_ID, draft(true), NOW);

        assertThat(product.getId()).isNotNull();
        assertThat(product.getOwnerId()).isEqualTo(OWNER_ID);
        assertThat(product.getName()).isEqualTo("Nike Air");
        assertThat(product.getCategory()).isEqualTo("shoe");
        assertThat(product.getUnit()).isEqualTo("pair");
        assertThat(product.getAttributes()).containsEntry("size", 42);
        assertThat(product.getPrice()).isEqualTo(85_000_000L);
        assertThat(product.getStatus()).isEqualTo(ProductStatus.ACTIVE);
        assertThat(product.isActive()).isTrue();
        assertThat(product.getCreatedAt()).isEqualTo(NOW);
        assertThat(product.getUpdatedAt()).isEqualTo(NOW);
    }

    @Test
    void createWithActiveFalseStartsArchived() {
        Product product = Product.create(OWNER_ID, draft(false), NOW);

        assertThat(product.getStatus()).isEqualTo(ProductStatus.ARCHIVED);
        assertThat(product.isActive()).isFalse();
    }

    @Test
    void createWithNullAttributesUsesEmptyMap() {
        ProductDraft noAttributes = new ProductDraft("Ring", null, "gold", "gram",
                null, 92_000_000L, DigitalDeliveryType.NONE, null, null, true);

        Product product = Product.create(OWNER_ID, noAttributes, NOW);

        assertThat(product.getAttributes()).isEmpty();
    }

    @Test
    void updateChangesFieldsAndStatus() {
        Product product = Product.create(OWNER_ID, draft(true), NOW);
        Instant later = NOW.plusSeconds(60);

        product.update(draft(false), later);

        assertThat(product.getStatus()).isEqualTo(ProductStatus.ARCHIVED);
        assertThat(product.getUpdatedAt()).isEqualTo(later);
        assertThat(product.getCreatedAt()).isEqualTo(NOW);
    }

    @Test
    void archiveSoftDeletes() {
        Product product = Product.create(OWNER_ID, draft(true), NOW);
        Instant later = NOW.plusSeconds(60);

        product.archive(later);

        assertThat(product.getStatus()).isEqualTo(ProductStatus.ARCHIVED);
        assertThat(product.isActive()).isFalse();
        assertThat(product.getUpdatedAt()).isEqualTo(later);
    }

    @Test
    void belongsToChecksOwnership() {
        Product product = Product.create(OWNER_ID, draft(true), NOW);

        assertThat(product.belongsTo(OWNER_ID)).isTrue();
        assertThat(product.belongsTo(UUID.randomUUID())).isFalse();
    }

    @Test
    void createRejectsBlankRequiredFields() {
        ProductDraft blankName = new ProductDraft(" ", null, "shoe", "pair",
                Map.of(), 1L, DigitalDeliveryType.NONE, null, null, true);

        assertThatThrownBy(() -> Product.create(OWNER_ID, blankName, NOW))
                .isInstanceOf(BusinessException.class)
                .extracting(e -> ((BusinessException) e).errorCode())
                .isEqualTo(ErrorCode.VALIDATION_FAILED);
    }

    @Test
    void createRejectsNonPositivePrice() {
        ProductDraft free = new ProductDraft("Ring", null, "gold", "gram",
                Map.of(), 0L, DigitalDeliveryType.NONE, null, null, true);

        assertThatThrownBy(() -> Product.create(OWNER_ID, free, NOW))
                .isInstanceOf(BusinessException.class)
                .extracting(e -> ((BusinessException) e).errorCode())
                .isEqualTo(ErrorCode.VALIDATION_FAILED);
    }
}
