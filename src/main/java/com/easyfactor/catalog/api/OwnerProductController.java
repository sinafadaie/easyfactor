package com.easyfactor.catalog.api;

import java.util.UUID;

import com.easyfactor.catalog.api.dto.CreateProductRequest;
import com.easyfactor.catalog.api.dto.ProductResponse;
import com.easyfactor.catalog.api.dto.UpdateProductRequest;
import com.easyfactor.catalog.service.ProductService;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.shared.api.ApiResponse;
import com.easyfactor.shared.api.PageResponse;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Owner-facing product management API.
 */
@RestController
@RequestMapping("/api/v1/owner/products")
public class OwnerProductController {

    private final ProductService productService;

    public OwnerProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ApiResponse<ProductResponse> create(@AuthenticationPrincipal AuthenticatedUser owner,
            @Valid @RequestBody CreateProductRequest request) {
        var product = productService.create(owner.accountId(), request.toDraft());
        return ApiResponse.ok(ProductResponse.from(product));
    }

    @PutMapping("/{productId}")
    ApiResponse<ProductResponse> update(@AuthenticationPrincipal AuthenticatedUser owner,
            @PathVariable UUID productId, @Valid @RequestBody UpdateProductRequest request) {
        var product = productService.update(owner.accountId(), productId, request.toDraft());
        return ApiResponse.ok(ProductResponse.from(product));
    }

    @DeleteMapping("/{productId}")
    ApiResponse<Void> archive(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID productId) {
        productService.archive(owner.accountId(), productId);
        return ApiResponse.ok(null);
    }

    @GetMapping
    ApiResponse<PageResponse<ProductResponse>> list(@AuthenticationPrincipal AuthenticatedUser owner, Pageable pageable) {
        var page = productService.listOwned(owner.accountId(), pageable).map(ProductResponse::from);
        return ApiResponse.ok(PageResponse.from(page));
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> get(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID productId) {
        var product = productService.getOwned(owner.accountId(), productId);
        return ApiResponse.ok(ProductResponse.from(product));
    }
}
