package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.ExchangeResponseModel;
import com.assignment.KiranaService.model.TransactionRequestModel;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import com.assignment.KiranaService.repository.TransactionRepository;
import com.assignment.KiranaService.utility.ExchangeResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.assignment.KiranaService.utility.Constants.USD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ExchangeResponseUtil exchangeResponseUtil;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionSummaryOn_shouldReturnTransactionSummary() {
        // Arrange
        LocalDate currentDate = LocalDate.now();
        when(transactionRepository.getTransactionSummary(currentDate)).thenReturn(createMockTransactionSummary());

        // Act
        TransactionSummaryDto result = transactionService.getTransactionSummaryOn(currentDate);

        // Assert
        assertNotNull(result);
        assertEquals(Date.valueOf(currentDate), result.getTransactionDate());
        assertEquals(100.0, result.getCreditAmount());
        assertEquals(50.0, result.getDebitAmount());
    }
    @Test
    void saveTransaction_shouldSaveTransaction() {
        // Arrange
        TransactionRequestModel requestModel = createMockTransactionRequestModel();
        when(exchangeResponseUtil.getExchangeRateInfo()).thenReturn(createMockExchangeResponse());
        when(transactionRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UUID result = transactionService.saveTransaction(requestModel);

        // Assert
        assertNotNull(result);
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void getTransactionsByDate_shouldReturnListOfTransactions() {
        // Arrange
        LocalDate currentDate = LocalDate.now();
        when(transactionRepository.findAllByTransactionTimeBetween(any(), any()))
                .thenReturn(Collections.singletonList(createMockTransaction()));

        // Act
        List<Transaction> result = transactionService.getTransactionsByDate(currentDate);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(currentDate, result.get(0).getTransactionTime().toLocalDate());
    }


    private TransactionRequestModel createMockTransactionRequestModel() {
        return  TransactionRequestModel.builder()
                .transactionType("CREDIT")
                .amount(50.0)
                .currency(USD)
                .transactionDesc("Purchase of goods")
                .paymentMode("CASH")
                .creationUser("JohnDoe")
                .build();
    }

    private Transaction createMockTransaction() {
        return Transaction.builder()
                .transactionId(UUID.randomUUID())
                .creditAmount(50.0)
                .debitAmount(0.0)
                .transactionTime(LocalDateTime.now())
                .transactionDetails("Purchase of goods")
                .paymentMode("CASH")
                .creationUser("JohnDoe")
                .build();
    }

    private TransactionSummaryDto createMockTransactionSummary() {
        return TransactionSummaryDto.builder()
                .transactionDate(Date.valueOf(LocalDate.now()))
                .creditAmount(100.0)
                .debitAmount(50.0)
                .build();
    }

    private ExchangeResponseModel createMockExchangeResponse() {
        return  ExchangeResponseModel.builder()
                .baseCurrency(USD)
                .rates(Collections.singletonMap("INR", 2.0))
                .build();
    }
}
