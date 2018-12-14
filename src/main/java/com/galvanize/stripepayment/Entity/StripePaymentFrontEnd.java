package com.galvanize.stripepayment.Entity;

import lombok.Builder;

public class StripePaymentFrontEnd {

    /**
     *  amount=2000 \
     *    -d currency=usd \
     *    -d source=tok_visa \
     *    -d description="Charge for jenny.rosen@example.com"
     */
    double amount;
    String currency;
    String source;
    String description;

    @Builder
    public StripePaymentFrontEnd(double amount, String currency, String source, String description){
        this.amount = amount;
        this.currency = currency;
        this.source = source;
        this.description = description;
    }
}
