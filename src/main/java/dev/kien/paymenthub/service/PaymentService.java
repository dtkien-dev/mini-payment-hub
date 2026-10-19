package dev.kien.paymenthub.service;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final PaymentValidator validator;

    public PaymentService(PaymentValidator paymentValidator) {
        this.validator = paymentValidator;
        System.out.println("[PaymentService] constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("[PaymentService] PostConstruct");
    }

    public boolean validatePayment(BigDecimal amount) {
        return validator.isValidAmount(amount);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("[PaymentService] PreDestroy");
    }

}
