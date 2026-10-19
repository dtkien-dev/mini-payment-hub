package dev.kien.paymenthub.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record PaymentValidationRequest(
        @NotNull(message = "Amount is required") @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than zero") BigDecimal amount) {
}