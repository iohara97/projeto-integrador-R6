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
public class RelatorioEstoqueControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarUmRelatorioEstoqueDTO() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/stock-report?estoqueMinimo=50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveRetornarUmRelatorioEstoqueDTOPorCategoria() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/stock-report/filterCategory?categoria=fresco&estoqueMinimo=1000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveRetornarUmRelatorioEstoqueDTOPorSetor() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/stock-report/filterSector?setorId=1&estoqueMinimo=1000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveRetornarUmRelatorioEstoqueDTOPorArmazem() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/stock-report/filterStorage?armazemId=1&estoqueMinimo=1000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
