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
@WithMockUser(username = "iohara", password = "12345", authorities = "Comprador")
public class CarrinhoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarCarrinho() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void deveCadastrarCarrinho() throws Exception {

        String payLoad = "{\n" +
                "  \"dataCriacao\": \"2022-01-27T10:27:58.331085\",\n" +
                "  \"comprador_id\": 1,\n" +
                "  \"status\": 0,\n" +
                "  \"listaAnuncio\": [\n" +
                "    {\n" +
                "      \"anuncio_id\": 1,\n" +
                "      \"quantidade\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"anuncio_id\": 2,\n" +
                "      \"quantidade\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/fresh-products/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                );
    }

    @Test
    public void deveAtualizarCarrinho() throws Exception {

        String payLoad = "{\n" +
                "  \"dataCriacao\": \"2022-01-27T10:27:58.331085\",\n" +
                "  \"comprador_id\": 1,\n" +
                "  \"status\": 1,\n" +
                "  \"listaAnuncio\": [\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "      \"anuncio_id\": 1,\n" +
                "      \"quantidade\": 2\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "      \"anuncio_id\": 2,\n" +
                "      \"quantidade\": 4\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/v1/fresh-products/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                );
    }
}
