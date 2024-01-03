package com.assignment.KiranaService.service;

import com.assignment.KiranaService.repository.TransactionRepository;
import com.assignment.KiranaService.utility.ExchangeResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ITransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ExchangeResponseUtil exchangeResponseUtil;

    @InjectMocks
    private ITransactionService ITransactionService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetTransactionsWithNonNullDate() {
//        LocalDate testDate = LocalDate.of(2022, 1, 1);
//        TransactionSummaryDto mockSummaryDto = new TransactionSummaryDto(0.0, 100.0, testDate);
//
//        when(transactionRepository.getTransactionSummaryByDate(testDate)).thenReturn(mockSummaryDto);
//
//        TransactionSummaryDto result = handleTransactionRecord.g(testDate);
//
//        assertEquals(mockSummaryDto, result);
//    }





}
