package dev.kien.paymenthub.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentValidator {
    public boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
