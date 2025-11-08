package com.replit.example.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for actuator endpoints
 */
@SpringBootTest
@AutoConfigureMockMvc
class ActuatorEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpointShouldReturnUp() throws Exception {
        mockMvc.perform(get("/actuator/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void infoEndpointShouldBeAccessible() throws Exception {
        mockMvc.perform(get("/actuator/info"))
            .andExpect(status().isOk());
    }

    @Test
    void metricsEndpointShouldBeAccessible() throws Exception {
        mockMvc.perform(get("/actuator/metrics"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.names").isArray());
    }

    @Test
    void prometheusEndpointShouldBeAccessible() throws Exception {
        mockMvc.perform(get("/actuator/prometheus"))
            .andExpect(status().isOk());
    }
}
