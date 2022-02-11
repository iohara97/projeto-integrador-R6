package com.example.demo.serviceTest;

import com.example.demo.dto.EstoqueSetorIdDataValidadeDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.Tipos;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.service.EstoqueService;
import com.example.demo.utils.MockEstoque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstoqueServiceTest {

    private MockEstoque mockEstoque;
    private List<Estoque> listaEstoqueFiltrada = new ArrayList<>();
    private LocalDate dataValidade;
    private EstoqueService estoqueService;
    private long numeroDias;

    @BeforeEach
    public void init() {

        mockEstoque = new MockEstoque();
        listaEstoqueFiltrada = mockEstoque.getListaEstoqueFiltrada();
        dataValidade = mockEstoque.getDataValidade();
        numeroDias = mockEstoque.getNumeroDias();

        EstoqueRepository estoqueRepository = Mockito.mock(EstoqueRepository.class);
        Mockito.when(estoqueRepository.getAnunciosBySetorValidadeRep(1L, 1L, dataValidade)).thenReturn(listaEstoqueFiltrada);
        Mockito.when(estoqueRepository.getEstoqueByDataValidadeCategoria(dataValidade, 2)).thenReturn(listaEstoqueFiltrada);

        estoqueService = new EstoqueService(estoqueRepository);

    }

    @Test
    public void deveRetornarTodosEstoquesPorSetorIdEDataValidade(){

        List<EstoqueSetorIdDataValidadeDTO> listaEstoqueService = estoqueService.getAllEstoqueBySetorIdDataValidade(1L, numeroDias);
        List<EstoqueSetorIdDataValidadeDTO> listaEstoqueMock = EstoqueSetorIdDataValidadeDTO.converte(listaEstoqueFiltrada);
        assert(listaEstoqueMock.equals(listaEstoqueService));
    }

    @Test
    public void deveRetornarTodosEstoquesPorCategoriaEDataValidade(){

        List<EstoqueSetorIdDataValidadeDTO> listaEstoqueService = estoqueService.getEstoqueByDataValidadeCategoria(numeroDias, 2, "asc");

        List<EstoqueSetorIdDataValidadeDTO> listaEstoqueMock = EstoqueSetorIdDataValidadeDTO.converte(listaEstoqueFiltrada);
        assert(listaEstoqueMock.equals(listaEstoqueService));
    }
}
