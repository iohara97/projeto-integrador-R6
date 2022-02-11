package com.example.demo.serviceTest;

import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import com.example.demo.utils.MockProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProdutoServiceTest {

    private MockProduto mockProduto;
    private Produto produto;
    private ProdutoService produtoService;

    @BeforeEach
    public void init() {

        mockProduto = new MockProduto();
        produto = mockProduto.getProduto();

    }

    @Test
    public void deveVerificarProdutoSalvo() {

        ProdutoRepository mock = Mockito.mock(ProdutoRepository.class);
        Mockito.when(mock.save(produto)).thenReturn(produto);
        ProdutoService produtoService = new ProdutoService(mock);
        Produto produtoCriado = produtoService.save(produto);

        assertTrue(produtoCriado.equals(produto));

    }

}
