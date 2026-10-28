package com.easyfactor.invoice.api;

import java.util.UUID;

import com.easyfactor.invoice.api.dto.InvoiceResponse;
import com.easyfactor.invoice.service.InvoiceService;
import com.easyfactor.shared.api.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public invoice visibility API. No auth: the unguessable token in the link
 * IS the secret (like Stripe payment links).
 */
@RestController
@RequestMapping("/api/v1/public/invoices")
public class PublicInvoiceController {

    private final InvoiceService invoiceService;

    public PublicInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{publicToken}")
    ApiResponse<InvoiceResponse> get(@PathVariable UUID publicToken) {
        var invoice = invoiceService.getByPublicToken(publicToken);
        return ApiResponse.ok(InvoiceResponse.from(invoice));
    }
}
