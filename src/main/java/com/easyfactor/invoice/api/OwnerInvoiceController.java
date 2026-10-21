package com.easyfactor.invoice.api;

import java.util.UUID;

import com.easyfactor.invoice.api.dto.CreateInvoiceRequest;
import com.easyfactor.invoice.api.dto.InvoiceResponse;
import com.easyfactor.invoice.domain.InvoiceStatus;
import com.easyfactor.invoice.service.InvoiceService;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.shared.api.ApiResponse;
import com.easyfactor.shared.api.PageResponse;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Owner-facing invoice lifecycle API (manual invoicing).
 */
@RestController
@RequestMapping("/api/v1/owner/invoices")
public class OwnerInvoiceController {

    private final InvoiceService invoiceService;

    public OwnerInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    ApiResponse<InvoiceResponse> create(@AuthenticationPrincipal AuthenticatedUser owner,
            @Valid @RequestBody CreateInvoiceRequest request) {
        // TODO
        return null;
    }

    @PostMapping("/{invoiceId}/send")
    ApiResponse<InvoiceResponse> send(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID invoiceId) {
        // TODO
        return null;
    }

    @PostMapping("/{invoiceId}/cancel")
    ApiResponse<InvoiceResponse> cancel(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID invoiceId,
            @RequestParam String reason) {
        // TODO
        return null;
    }

    @GetMapping
    ApiResponse<PageResponse<InvoiceResponse>> list(@AuthenticationPrincipal AuthenticatedUser owner,
            @RequestParam(required = false) InvoiceStatus status, Pageable pageable) {
        // TODO
        return null;
    }

    @GetMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> get(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID invoiceId) {
        // TODO
        return null;
    }
}
