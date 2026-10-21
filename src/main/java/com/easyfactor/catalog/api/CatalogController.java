package com.easyfactor.catalog.api;

import java.util.UUID;

import com.easyfactor.catalog.api.dto.PublicProductResponse;
import com.easyfactor.catalog.service.ProductService;
import com.easyfactor.shared.api.ApiResponse;
import com.easyfactor.shared.api.PageResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Customer-facing public catalog API. Never exposes delivery payloads.
 */
@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/owners/{ownerId}/products")
    ApiResponse<PageResponse<PublicProductResponse>> listByOwner(@PathVariable UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    @GetMapping("/products/{productId}")
    ApiResponse<PublicProductResponse> get(@PathVariable UUID productId) {
        // TODO
        return null;
    }
}
