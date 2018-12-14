package com.galvanize.stripepayment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.stripepayment.Entity.StripePaymentFrontEnd;
import com.galvanize.stripepayment.controller.StripeController;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(value = StripeController.class, secure = false)
public class StripeControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StripeControllerTest.class);
    @Autowired
    MockMvc mockMvc;
    @Autowired
    StripeController controller;
    RestTemplate restTemplate;
    ResponseEntity response;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));

    @Before
    public void setup() throws Exception {
        restTemplate = new RestTemplate();
        response = null;

    }


    String s = " \'id\': \'ch_1DgzUF2eZvKYlo2CJcMNgtr6\' " ;

      String result ="\'id\': \'ch_1DgzUF2eZvKYlo2CJcMNgtr6\',\n" + " \'object\': \'charge\',\n"+
                    "  \"amount\": 100000,\n"+
            "  \"amount_refunded\": 0,\n"+
            "  \"application\": null,\n"+
            "  \"application_fee\": null,\n"+
            "  \"balance_transaction\": \"txn_19XJJ02eZvKYlo2ClwuJ1rbA\",\n"+
            "  \"captured\": false,\n"+
            "  \"created\": 1544727883,\n"+
            "  \"currency\": \"usd\",\n"+
            "  \"customer\": \"cus_E9I4fj0YA2RCCd\",\n"+
            "  \"description\": \"kt97dlf09wy2053mw211jeu1\",\n"+
            "  \"destination\": null,\n"+
            "  \"dispute\": null,\n"+
            "  \"failure_code\": \"card_declined\",\n"+
            "  \"failure_message\": \"Your card was declined.\",\n"+
            "  \"fraud_details\": {\n"+
            "  },\n"+
            "  \"invoice\": null,\n"+
            "  \"livemode\": false,\n"+
            "  \"metadata\": {\n"+
            "  },\n"+
            "  \"on_behalf_of\": null,\n"+
            "  \"order\": null,\n"+
            "  \"outcome\": {\n"+
            "    \"network_status\": \"not_sent_to_network\",\n"+
            "    \"reason\": \"highest_risk_level\",\n"+
            "    \"risk_level\": \"highest\",\n"+
            "    \"risk_score\": 91,\n"+
            "    \"rule\": \"block_if_high_risk__enabled\",\n"+
            "    \"seller_message\": \"Stripe blocked this payment as too risky.\",\n"+
            "    \"type\": \"blocked\"\n"+
            "  },\n"+
            "  \"paid\": false,\n"+
            "  \"payment_intent\": null,\n"+
            "  \"receipt_email\": null,\n"+
            "  \"receipt_number\": null,\n"+
            "  \"refunded\": false,\n"+
            "  \"refunds\": {\n"+
            "    \"object\": \"list\",\n"+
            "    \"data\": [\n"+
            "\n"+
            "    ],\n"+
            "    \"has_more\": false,\n"+
            "    \"total_count\": 0,\n"+
            "    \"url\": \"/v1/charges/ch_1DgzUF2eZvKYlo2CJcMNgtr6/refunds\"\n"+
            "  },\n"+
            "  \"review\": null,\n"+
            "  \"shipping\": null,\n"+
            "  \"source\": {\n"+
            "    \"id\": \"card_1DgzUB2eZvKYlo2Cf4Y71vbA\",\n"+
            "    \"object\": \"card\",\n"+
            "    \"address_city\": null,\n"+
            "    \"address_country\": null,\n"+
            "    \"address_line1\": null,\n"+
            "    \"address_line1_check\": null,\n"+
            "    \"address_line2\": null,\n"+
            "    \"address_state\": null,\n"+
            "    \"address_zip\": null,\n"+
            "    \"address_zip_check\": null,\n"+
            "    \"brand\": \"Visa\",\n"+
            "    \"country\": \"US\",\n"+
            "    \"customer\": \"cus_E9I4fj0YA2RCCd\",\n"+
            "    \"cvc_check\": \"unavailable\",\n"+
            "    \"dynamic_last4\": null,\n"+
            "    \"exp_month\": 12,\n"+
            "    \"exp_year\": 2022,\n"+
            "    \"fingerprint\": \"yTFMYiPXiPa5AoSn\",\n"+
            "    \"funding\": \"credit\",\n"+
            "    \"last4\": \"4954\",\n"+
            "    \"metadata\": {\n"+
            "    },\n"+
            "    \"name\": \"muneeb@gmail.com\",\n"+
            "    \"tokenization_method\": null\n"+
            "  },\n"+
            "  \"source_transfer\": null,\n"+
            "  \"statement_descriptor\": null,\n"+
            "  \"status\": \"failed\",\n"+
            "  \"transfer_group\": null" ;
    @Test
    public void sendPaymentTest() throws Exception{
        stubFor(get(urlEqualTo("https://api.stripe.com/v1/charges"))
                        .willReturn(aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", TEXT_PLAIN_VALUE)
                                        .withBody(result)));

        response = restTemplate.getForEntity("http://localhost:8089/payment/create", String.class);

        assertThat("Verify Response Body", response.getBody().contains(result));
        assertThat("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));
        verify(getRequestedFor(urlMatching("/payment/create.*")));

    }

}





