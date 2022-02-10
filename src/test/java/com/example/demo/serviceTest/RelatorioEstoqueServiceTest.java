package com.example.demo.serviceTest;

import com.example.demo.dto.RelatorioEstoqueDTO;
import com.example.demo.entity.Estoque;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.service.RelatorioEstoqueService;
import com.example.demo.utils.MockAnuncios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelatorioEstoqueServiceTest {

    private MockAnuncios mockAnuncios;
    private List<Estoque> estoqueLista;
    private RelatorioEstoqueService relatorioEstoqueService;

    @BeforeEach
    public void init() {

        mockAnuncios = new MockAnuncios();
        estoqueLista = mockAnuncios.getEstoqueList();

        EstoqueRepository mockEstoqueRepository = Mockito.mock(EstoqueRepository.class);

        Mockito.when(mockEstoqueRepository.findAll()).thenReturn(estoqueLista);

        relatorioEstoqueService = new RelatorioEstoqueService(mockEstoqueRepository);
    }

    @Test
    public void deveRetornarRelatorioEstoqueComDoisEstoquesMinimos() {

        RelatorioEstoqueDTO relatorioEstoqueDTO =  relatorioEstoqueService.getRelatorioEstoque(200);
        assertEquals(relatorioEstoqueDTO.getProdutosEstoqueBaixo().size(), 2);
    }

    @Test
    public void deveRetornarRelatorioEstoquePorCategoriaComUmEstoqueMinimo() {

        RelatorioEstoqueDTO relatorioEstoqueDTO =  relatorioEstoqueService.getRelatorioEstoquePorCategoria("Fresco", 50);
        assertEquals(relatorioEstoqueDTO.getProdutosEstoqueBaixo().size(), 1);
    }

    @Test
    public void deveRetornarRelatorioEstoquePorSetorComUmEstoqueMinimo() {

        RelatorioEstoqueDTO relatorioEstoqueDTO =  relatorioEstoqueService.getRelatorioEstoquePorSetor(1L, 50);
        assertEquals(relatorioEstoqueDTO.getProdutosEstoqueBaixo().size(), 1);
    }

    @Test
    public void deveRetornarRelatorioEstoquePorArmazemComDoisEstoquesMinimos() {

        RelatorioEstoqueDTO relatorioEstoqueDTO =  relatorioEstoqueService.getRelatorioEstoquePorArmazem(1L, 100);
        assertEquals(relatorioEstoqueDTO.getProdutosEstoqueBaixo().size(), 2);
    }
}
