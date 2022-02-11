package com.example.demo.serviceTest;

import com.example.demo.dto.RelatorioEstoqueDTO;
import com.example.demo.entity.Armazem;
import com.example.demo.entity.Estoque;
import com.example.demo.entity.Setor;
import com.example.demo.repository.ArmazemRepository;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.SetorRepository;
import com.example.demo.service.RelatorioEstoqueService;
import com.example.demo.utils.MockAnuncios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RelatorioEstoqueServiceTest {

    private MockAnuncios mockAnuncios;
    private List<Estoque> estoqueLista;
    private RelatorioEstoqueService relatorioEstoqueService;
    private Setor setor;
    private Armazem armazem;

    @BeforeEach
    public void init() {

        mockAnuncios = new MockAnuncios();
        estoqueLista = mockAnuncios.getEstoqueList();
        setor = mockAnuncios.getSetor1();
        armazem = mockAnuncios.getArmazem1();

        EstoqueRepository mockEstoqueRepository = Mockito.mock(EstoqueRepository.class);
        SetorRepository mockSetorRepository = Mockito.mock(SetorRepository.class);
        ArmazemRepository mockArmazemRepository = Mockito.mock(ArmazemRepository.class);

        Mockito.when(mockEstoqueRepository.findAll()).thenReturn(estoqueLista);
        Mockito.when(mockSetorRepository.findById(1L)).thenReturn(Optional.of(setor));
        Mockito.when(mockArmazemRepository.findById(1L)).thenReturn(Optional.of(armazem));

        relatorioEstoqueService = new RelatorioEstoqueService(mockEstoqueRepository, mockSetorRepository, mockArmazemRepository);
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
