package com.replit.example.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void shouldCreateErrorResponseWithTimestamp() {
        ErrorResponse response = new ErrorResponse("Error", "Details");

        assertEquals("Error", response.getMessage());
        assertEquals("Details", response.getDetails());
        assertNotNull(response.getTimestamp());
        assertTrue(response.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void shouldCreateErrorResponseWithExplicitTimestamp() {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse("Error", "Details", timestamp);

        assertEquals("Error", response.getMessage());
        assertEquals("Details", response.getDetails());
        assertEquals(timestamp, response.getTimestamp());
    }

    @Test
    void shouldSupportLombokGeneratedMethods() {
        ErrorResponse response1 = new ErrorResponse("Error", "Details");
        ErrorResponse response2 = new ErrorResponse("Error", "Details");

        // Test toString (from @Data)
        assertNotNull(response1.toString());
        assertTrue(response1.toString().contains("Error"));

        // Test setters (from @Data)
        response2.setMessage("New Error");
        assertEquals("New Error", response2.getMessage());
    }
}
