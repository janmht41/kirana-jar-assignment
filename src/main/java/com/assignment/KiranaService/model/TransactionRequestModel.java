package com.assignment.KiranaService.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.assignment.KiranaService.utility.Constants.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private Double amount;

    @NotBlank
    private String creationUser;

    @NotBlank(message = "currency can't be blank")
    @Pattern(regexp = ALLOWED_TRANSACTION_CURRENCY, message = "supports only INR/USD transactions ")
    private String currency;

    @Pattern(regexp = ALLOWED_TRANSACTION_TYPE, message = "supports only debit or credit transaction")
    @NotBlank
    private String transactionType;

    private String transactionDesc;

    @Pattern(regexp =ALLOWED_PAYMENT_METHODS, message ="supported payment methods are CASH/UPI/CARD" )
    private  String paymentMode;

}
