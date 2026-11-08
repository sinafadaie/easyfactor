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

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    ApiResponse<ProductResponse> create(@AuthenticationPrincipal AuthenticatedUser owner,       //Spring MVC's AuthenticationPrincipalArgumentResolve will inject
            @Valid @RequestBody CreateProductRequest request) {                                 //AuthenticatedUser obj (it is not Bean)
        var product = productService.create(owner.accountId(), request.toDraft());                       //Each HTTP request runs on its own thread, so each request has its own context
        return ApiResponse.ok(ProductResponse.from(product));                                           //The security context lives in a ThreadLocal (SecurityContextHolder).
    }                                                                                                   //That's why a controller method can have no idea how authentication happened!

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
    ApiResponse<PageResponse<ProductResponse>> list(@AuthenticationPrincipal AuthenticatedUser owner, Pageable pageable) {  //we can send query parameter
        Pageable withDefaultSort = PageRequest.of(                                                                                           //on every end point that consume pageable obj
                pageable.getPageNumber(),                                                                                                    //spring MVC handle it
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "createdAt")));
        var page = productService.listOwned(owner.accountId(), withDefaultSort).map(ProductResponse::from);
        return ApiResponse.ok(PageResponse.from(page));
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> get(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID productId) {
        var product = productService.getOwned(owner.accountId(), productId);
        return ApiResponse.ok(ProductResponse.from(product));
    }
}
