package dev.kien.paymenthub.api.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> details = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            details.putIfAbsent(error.getField(), error.getDefaultMessage());
        });

        ApiError response = new ApiError("VALIDATION_ERROR", "Invalid request data", details, Instant.now());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMalformedJson(HttpMessageNotReadableException ex) {
        ApiError response = new ApiError("MALFORMED_REQUEST", "Request body is missing or invalid", Map.of(),
                Instant.now());
        return ResponseEntity.badRequest().body(response);
    }

}
