package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.model.TransactionRequest;

import com.assignment.KiranaService.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    void shouldInvokeRecordTransaction() throws Exception{

        TransactionRequest transactionRequest  = TransactionRequest.builder()
                .transactionDesc("dummy")
                .transactionType("CREDIT")
                .amount(38.32)
                .currency("USD")
                .paymentMode("CASH")
                .build();

        mockMvc.perform((post("/record"))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isOk());

        verify(transactionService, times(1)).saveTransaction(transactionRequest);
    }

}
