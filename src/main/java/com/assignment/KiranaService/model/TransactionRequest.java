package com.assignment.KiranaService.model;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.math.BigDecimal;

import static com.assignment.KiranaService.utility.Constants.TRANSACTION_CURRENCY;
import static com.assignment.KiranaService.utility.Constants.TRANSACTION_TYPE;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private BigDecimal amount;
    @Pattern(regexp = TRANSACTION_CURRENCY, message = "supports only INR/USD transactions ")
    private String currency;
    @Pattern(regexp = TRANSACTION_TYPE, message = "supports only debit or credit transaction")
    private String transactionType;
    private String transactionDesc;

    public BigDecimal getAmount() {
        return amount;
    }


    public String getTransactionType() {
        return transactionType;
    }


    public String getTransactionDesc() {
        return transactionDesc;
    }


}
