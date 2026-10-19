package dev.kien.paymenthub.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAcceptValidAmount() throws Exception {
        mockMvc.perform(post("/api/v1/payments/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"amount": 100000}
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true))
                .andExpect(jsonPath("$.amount").value(100000));
    }

    @Test
    void shouldRejectNegativeAmount() throws Exception {
        mockMvc.perform(post("/api/v1/payments/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"amount": -100}
                    """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code")
                        .value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.details.amount")
                        .value("Amount must be greater than zero"));
    }

    @Test
    void shouldRejectMissingAmount() throws Exception {
        mockMvc.perform(post("/api/v1/payments/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code")
                        .value("VALIDATION_ERROR"));
    }

    @Test
    void shouldRejectInvalidAmountType() throws Exception {
        mockMvc.perform(post("/api/v1/payments/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"amount": "abc"}
                    """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code")
                        .value("MALFORMED_REQUEST"));
    }
}