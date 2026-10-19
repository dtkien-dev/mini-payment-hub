package dev.kien.paymenthub.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.kien.paymenthub.service.PaymentService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import dev.kien.paymenthub.api.dto.PaymentValidationRequest;
import dev.kien.paymenthub.api.dto.PaymentValidationResponse;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/validate")
    public Map<String, Boolean> validate(
            @RequestParam BigDecimal amount) {
        return Map.of(
                "valid",
                paymentService.validatePayment(amount));
    }

    @PostMapping("/validate")
    public ResponseEntity<PaymentValidationResponse> validatePayment(
            @Valid @RequestBody PaymentValidationRequest request) {
        boolean valid = paymentService.validatePayment(request.amount());

        PaymentValidationResponse response = new PaymentValidationResponse(
                request.amount(),
                valid,
                "Payment amount is valid");

        return ResponseEntity.ok(response);
    }

}
