package dev.kien.paymenthub.bootstrap;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import dev.kien.paymenthub.service.PaymentService;

import java.math.BigDecimal;

@Component
public class PaymentStartupRunner implements CommandLineRunner {

    private final PaymentService paymentService;

    public PaymentStartupRunner(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void run(String... args) {
        System.out.println(
                "Payment 100000 valid: "
                        + paymentService.validatePayment(
                                new BigDecimal("100000")));
    }

}
