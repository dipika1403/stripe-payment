package com.galvanize.stripepayment.controller;

import com.galvanize.stripepayment.Entity.StripePaymentFrontEnd;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StripeController {

    @PostMapping("/create")

    public String sendPayment(StripePaymentFrontEnd stripePaymentFrontEnd){
        // create a charge to stripe API - https://stripe.com/docs/api - create a charge
        //https://api.stripe.com/v1/charges
        // return response

        return "result";

    }

}
