package com.easyfactor.invoice.api;

import java.util.UUID;

import com.easyfactor.invoice.api.dto.InvoiceResponse;
import com.easyfactor.invoice.service.InvoiceService;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.shared.api.ApiResponse;
import com.easyfactor.shared.api.PageResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Customer-facing invoice visibility API.
 */
@RestController
@RequestMapping("/api/v1/customer/invoices")
public class CustomerInvoiceController {

    private final InvoiceService invoiceService;

    public CustomerInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    ApiResponse<PageResponse<InvoiceResponse>> list(@AuthenticationPrincipal AuthenticatedUser customer, Pageable pageable) {
        // TODO
        return null;
    }

    @GetMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> get(@AuthenticationPrincipal AuthenticatedUser customer, @PathVariable UUID invoiceId) {
        // TODO
        return null;
    }
}
