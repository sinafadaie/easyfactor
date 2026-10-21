package com.easyfactor.payment.gateway;

import java.time.Clock;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.payment.domain.PaymentGateway;
import com.easyfactor.shared.domain.PaymentProvider;
import com.easyfactor.telegram.domain.TelegramBotApiClient;

import org.springframework.stereotype.Component;

/**
 * Telegram Stars (XTR) gateway strategy: sends an invoice via the Bot API
 * with provider_token="" and currency=XTR; payload carries the internal invoice id.
 */
@Component
public class TelegramStarsGateway implements PaymentGateway {

    private final TelegramBotApiClient botApiClient;
    private final Clock clock;

    public TelegramStarsGateway(TelegramBotApiClient botApiClient, Clock clock) {
        this.botApiClient = botApiClient;
        this.clock = clock;
    }

    @Override
    public PaymentProvider provider() {
        return PaymentProvider.TELEGRAM_STARS;
    }

    @Override
    public InitiationResult initiate(Invoice invoice) {
        // TODO: build TelegramSendInvoiceRequest (title, description, payload=invoice.id,
        // TODO: currency=XTR, prices from items) and botApiClient.sendInvoice(...)
        return null;
    }
}
