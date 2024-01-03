package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.model.TransactionRequestModel;
import com.assignment.KiranaService.service.ITransactionService;
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
    private ITransactionService ITransactionService;

    @Test
    void shouldInvokeRecordTransaction() throws Exception{

        TransactionRequestModel transactionRequestModel = TransactionRequestModel.builder()
                .transactionDesc("dummy")
                .transactionType("CREDIT")
                .amount(38.32)
                .currency("USD")
                .paymentMode("CASH")
                .creationUser("dummy")
                .build();

        mockMvc.perform((post("/record"))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transactionRequestModel)))
                .andExpect(status().isOk());

        verify(ITransactionService, times(1)).saveTransaction(transactionRequestModel);
    }

}
