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
import org.springframework.web.bind.annotation.PutMapping;
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
        var invoice = invoiceService.createInvoice(owner.accountId(), request.toDraft());
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }

    @PutMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> update(@AuthenticationPrincipal AuthenticatedUser owner,
            @PathVariable UUID invoiceId, @Valid @RequestBody CreateInvoiceRequest request) {
        var invoice = invoiceService.updateInvoice(owner.accountId(), invoiceId, request.toDraft());
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }

    @PostMapping("/{invoiceId}/paid")           //this is post, because we don't want to replace anything!
    ApiResponse<InvoiceResponse> markPaid(@AuthenticationPrincipal AuthenticatedUser owner,
            @PathVariable UUID invoiceId) {
        var invoice = invoiceService.markPaid(owner.accountId(), invoiceId);
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }

    @PostMapping("/{invoiceId}/dispatching")    //this is post, because we don't want to replace anything!
    ApiResponse<InvoiceResponse> markOnDispatching(@AuthenticationPrincipal AuthenticatedUser owner,
            @PathVariable UUID invoiceId) {
        var invoice = invoiceService.markOnDispatching(owner.accountId(), invoiceId);
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }

    @PostMapping("/{invoiceId}/dispatched")     //this is post, because we don't want to replace anything!
    ApiResponse<InvoiceResponse> markDispatched(@AuthenticationPrincipal AuthenticatedUser owner,
            @PathVariable UUID invoiceId) {
        var invoice = invoiceService.markDispatched(owner.accountId(), invoiceId);
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }

    @PostMapping("/{invoiceId}/cancel")         //this is post, because we don't want to replace anything!
    ApiResponse<InvoiceResponse> cancel(@AuthenticationPrincipal AuthenticatedUser owner,
            @PathVariable UUID invoiceId, @RequestParam String reason) {
        var invoice = invoiceService.cancelInvoice(owner.accountId(), invoiceId, reason);
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }

    @GetMapping
    ApiResponse<PageResponse<InvoiceResponse>> list(@AuthenticationPrincipal AuthenticatedUser owner,
            @RequestParam(required = false) InvoiceStatus status, Pageable pageable) {
        var page = invoiceService.listForOwner(owner.accountId(), status, pageable).map(InvoiceResponse::from);
        return ApiResponse.ok(PageResponse.from(page));
    }

    @GetMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> get(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID invoiceId) {
        var invoice = invoiceService.getForOwner(owner.accountId(), invoiceId);
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }
}
