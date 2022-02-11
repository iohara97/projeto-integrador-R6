package com.example.demo.controllerIntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "lucian", password = "12345", authorities = "Representante")
public class ArmazemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarUmArmazemDTO() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
