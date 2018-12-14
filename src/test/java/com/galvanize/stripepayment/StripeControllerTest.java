package com.galvanize.stripepayment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.stripepayment.Entity.StripePaymentFrontEnd;
import com.galvanize.stripepayment.controller.StripeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Test
    public void sendPaymentTest() throws Exception{

        StripePaymentFrontEnd mockPayment = StripePaymentFrontEnd.builder().build();
        LOGGER.info("Create mockPayment Request: {}", mockPayment);

        when(controller.sendPayment(mockPayment)).thenReturn("result");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(mockPayment))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andReturn();

        verify(controller).sendPayment(any(StripePaymentFrontEnd.class));
    }


}
