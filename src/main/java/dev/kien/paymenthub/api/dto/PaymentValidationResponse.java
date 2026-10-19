package dev.kien.paymenthub.api.dto;

import java.math.BigDecimal;

public record PaymentValidationResponse(
        BigDecimal amount,
        boolean valid,
        String message) {
}
