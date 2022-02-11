package com.example.demo.controllerIntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "lucian", password = "12345", authorities = "Representante")
public class OrdemCadastroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarUmaOrdemCadastro() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/inboundorder/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void deveRetornarCapacidadeSetor() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/capacidadeSetor/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveCadastrarUmaOrdemEntrada() throws Exception {

        String payLoad = "{\n" +
                "  \"ordemEntradaDTO\": {\n" +
                "    \"dataCriacao\": \"2022-01-27T10:27:58.331085\",\n" +
                "    \"setor_id\": 2\n" +
                "  },\n" +
                "  \"listaEstoqueDTO\": [\n" +
                "      {\n" +
                "        \"anuncio_id\": 2,\n" +
                "        \"quantidadeInicial\": 2,\n" +
                "        \"quantidadeAtual\": 3,\n" +
                "        \"temperaturaAtual\": 4.0,\n" +
                "        \"dataValidade\": \"2022-03-27\",\n" +
                "        \"dataProducao\": \"2022-01-27T10:27:58.331085\"\n" +
                "      }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
        .andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void deveAtualizarUmaOrdemEntrada() throws Exception {

        String payLoad = "{\n" +
                "  \"ordemEntradaDTO\": {\n" +
                "    \"dataCriacao\": \"2022-01-27T10:27:58.331085\",\n" +
                "    \"setor_id\": 2\n" +
                "  },\n" +
                "  \"listaEstoqueDTO\": [\n" +
                "      {\n" +
                "        \"anuncio_id\": 2,\n" +
                "        \"quantidadeInicial\": 5,\n" +
                "        \"quantidadeAtual\": 2,\n" +
                "        \"temperaturaAtual\": 4.0,\n" +
                "        \"dataValidade\": \"2022-03-27\",\n" +
                "        \"dataProducao\": \"2022-01-27T10:27:58.331085\"\n" +
                "      }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/v1/fresh-products/inboundorder/9")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                );
    }
}
