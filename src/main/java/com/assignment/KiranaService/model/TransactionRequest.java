package com.assignment.KiranaService.model;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import static com.assignment.KiranaService.utility.Constants.TRANSACTION_CURRENCY;
import static com.assignment.KiranaService.utility.Constants.TRANSACTION_TYPE;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Double amount;
    @Pattern(regexp = TRANSACTION_CURRENCY, message = "supports only INR/USD transactions ")
    private String currency;
    @Pattern(regexp = TRANSACTION_TYPE, message = "supports only debit or credit transaction")
    private String transactionType;
    private String transactionDesc;

}
