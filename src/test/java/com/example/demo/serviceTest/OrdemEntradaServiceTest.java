package com.example.demo.serviceTest;

import com.example.demo.dto.EstoqueDTO;
import com.example.demo.dto.InboundOrderDTO;
import com.example.demo.entity.*;
import com.example.demo.interfaces.CapacidadeSetor;
import com.example.demo.repository.AnuncioRepository;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.OrdemEntradaRepository;
import com.example.demo.repository.SetorRepository;
import com.example.demo.service.OrdemEntradaService;
import com.example.demo.utils.MockAnuncios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrdemEntradaServiceTest {

    private MockAnuncios mockAnuncios;
    private Setor setor;
    private Anuncio anuncio;
    private Estoque estoque;
    private OrdemEntrada ordemEntrada;
    private CapacidadeSetor capacidadeSetor;
    private List<Estoque> estoqueList;
    private OrdemEntradaService ordemEntradaService;
    private InboundOrderDTO inboundOrderDTO;

    @BeforeEach
    public void Init() {

        mockAnuncios = new MockAnuncios();
        anuncio = mockAnuncios.getAnuncios().get(0);
        setor = mockAnuncios.getSetor1();
        estoque = mockAnuncios.getEstoque1();
        ordemEntrada = mockAnuncios.getOrdemEntrada1();
        capacidadeSetor = mockAnuncios.getCapacidadeSetor();
        estoqueList = mockAnuncios.getEstoqueList();
        inboundOrderDTO = mockAnuncios.getInboundOrderDTO();

        OrdemEntradaRepository mockOrdemEntradaRepository = Mockito.mock(OrdemEntradaRepository.class);
        EstoqueRepository mockEstoqueRepository = Mockito.mock(EstoqueRepository.class);
        SetorRepository mockSetorRepository = Mockito.mock(SetorRepository.class);
        AnuncioRepository mockAnuncioRepository = Mockito.mock(AnuncioRepository.class);

        Mockito.when(mockSetorRepository.getById(setor.getId())).thenReturn(setor);
        Mockito.when(mockAnuncioRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(anuncio));
        Mockito.when(mockAnuncioRepository.getById(Mockito.any(Long.class))).thenReturn(anuncio);
        Mockito.when(mockEstoqueRepository.save(estoque)).thenReturn(estoque);
        Mockito.when(mockOrdemEntradaRepository.save(Mockito.any(OrdemEntrada.class))).thenReturn(ordemEntrada);
        Mockito.when(mockOrdemEntradaRepository.findById(1L)).thenReturn(Optional.of(ordemEntrada));
        Mockito.when(mockSetorRepository.getCapacidadeSetorById(setor.getId())).thenReturn(capacidadeSetor);
        Mockito.when(mockSetorRepository.findById(1L)).thenReturn(Optional.of(setor));
        Mockito.when(mockEstoqueRepository.findAllEstoque(ordemEntrada)).thenReturn(estoqueList);

        ordemEntradaService = new OrdemEntradaService(mockOrdemEntradaRepository, mockEstoqueRepository, mockSetorRepository, mockAnuncioRepository);
    }

    @Test
    public void deveSalvarUmaOrdemEntrada() {

        List<EstoqueDTO> estoqueDTO = ordemEntradaService.save(inboundOrderDTO);
        assertEquals(estoqueDTO.size(), 2);
    }

    @Test
    public void deveAtualizarUmaOrdemEntrada() {

        InboundOrderDTO inboundOrderDTOSaida = ordemEntradaService.update(inboundOrderDTO, 1L);
        assertEquals(inboundOrderDTO.getListaEstoqueDTO().size(), inboundOrderDTOSaida.getListaEstoqueDTO().size());
    }
}
