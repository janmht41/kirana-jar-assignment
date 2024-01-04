package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.service.TransactionServiceImpl;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionServiceImpl transactionService;
    @Test
    void getTransactions_shouldReturnListOfTransactions() throws Exception {
        LocalDate currentDate = LocalDate.now();
        when(transactionService.getTransactionsByDate(currentDate)).thenReturn(Collections.emptyList());

       mockMvc.perform(get("/transactions")
                .param("date", currentDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(transactionService, times(1)).getTransactionsByDate(currentDate);
    }

    @Test
    void getSummary_shouldReturnTransactionSummary() throws Exception {
        LocalDate currentDate = LocalDate.now();
        TransactionSummaryDto summaryDto = createMockTransactionSummary();
        when(transactionService.getTransactionSummaryOn(currentDate)).thenReturn(summaryDto);

        mockMvc.perform(get("/summary")
                .param("date", currentDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());

        verify(transactionService, times(1)).getTransactionSummaryOn(currentDate);
    }
    private TransactionSummaryDto createMockTransactionSummary() {
        return TransactionSummaryDto.builder()
                .transactionDate(Date.valueOf(LocalDate.now()))
                .creditAmount(100.0)
                .debitAmount(50.0)
                .build();
    }
}
