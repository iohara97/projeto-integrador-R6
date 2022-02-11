package com.example.demo.serviceTest;

import com.example.demo.dto.AnuncioExternoDTO;
import com.example.demo.dto.AnuncioInternoDTO;
import com.example.demo.entity.Anuncio;
import com.example.demo.enums.Tipos;
import com.example.demo.exception.AnunciosVaziosException;
import com.example.demo.repository.AnuncioRepository;
import com.example.demo.service.AnuncioService;
import com.example.demo.utils.MockAnuncios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AnuncioServiceTest {

    private MockAnuncios mockAnuncios;
    private Anuncio anuncio1;
    private AnuncioRepository repoMock;
    private LocalDate dataValidadeMais3Semanas;

    @BeforeEach
    void init() {
        mockAnuncios = new MockAnuncios();
        anuncio1 = mockAnuncios.getAnuncios().get(0);
        repoMock  = Mockito.mock(AnuncioRepository.class);
        dataValidadeMais3Semanas = LocalDate.now().plusWeeks(3);
    }

    @Test
    public void deveRetornarUmaListaDeAnunciosValidos() {
    //arrange
        List<Anuncio> anuncios = new ArrayList<>();
        anuncios.add(anuncio1);

        Mockito.when(repoMock.findAllAnunciosWithStockAndDueDateValid(dataValidadeMais3Semanas)).thenReturn(anuncios);

        AnuncioService anuncioService = new AnuncioService(repoMock);

        List<AnuncioExternoDTO> anunciosDTOEsperados = AnuncioExternoDTO.converte(anuncios);
    //act

        List<AnuncioExternoDTO> anunciosDTOObtidos = anuncioService.listAnunciosValidos();

    //assert
        assertTrue(anunciosDTOEsperados.get(0).getId().equals(anunciosDTOObtidos.get(0).getId()));

    }

    @Test
    public void deveRetornarUmaExceptionQuandoNaoEncontrarAnunciosValidos() {
        //arrange
        List<Anuncio> anuncios = new ArrayList<>();
//        anuncios.add(anuncio1);

        Mockito.when(repoMock.findAllAnunciosWithStockAndDueDateValid(dataValidadeMais3Semanas)).thenReturn(anuncios);

        AnuncioService anuncioService = new AnuncioService(repoMock);

        List<AnuncioExternoDTO> anunciosDTOEsperados = AnuncioExternoDTO.converte(anuncios);
        //act

        AnunciosVaziosException exceptionEsperada = assertThrows(AnunciosVaziosException.class,
                () -> anuncioService.listAnunciosValidos()
        );

        //assert
        assertEquals(exceptionEsperada.getMessage(), "Nenhum anúncio encontrado.");
    }

    @Test
    public void deveRetornarUmaListaDeAnunciosValidosPorCategoria() {
        //arrange
        LocalDate dataValidadeMais3Semanas = LocalDate.now().plusWeeks(3);

        // popular essa lista

        List<Anuncio> anuncios = new ArrayList<>();
        anuncios.add(anuncio1);

        Mockito
                .when(repoMock.findAllAnunciosByCategoryWithStockAndDueDateValid(dataValidadeMais3Semanas, Tipos.FRESCO))
                .thenReturn(anuncios);

        AnuncioService anuncioService = new AnuncioService(repoMock);

        List<AnuncioExternoDTO> anunciosDTOEsperados = AnuncioExternoDTO.converte(anuncios);
        //act

        List<AnuncioExternoDTO> anunciosDTOObtidos = anuncioService.listAnunciosByCategory(Tipos.FRESCO);

        //assert
        assertTrue(anunciosDTOEsperados.get(0).getId().equals(anunciosDTOObtidos.get(0).getId()));
    }


    @Test
    public void deveRetornarUmaExceptionQuandoNaoEncontrarAnunciosValidosPorCategoria() {
        //arrange
        List<Anuncio> anuncios = new ArrayList<>();
//        anuncios.add(anuncio1);

        Mockito
            .when(repoMock.findAllAnunciosByCategoryWithStockAndDueDateValid(dataValidadeMais3Semanas, Tipos.CONGELADO))
            .thenReturn(anuncios);

        AnuncioService anuncioService = new AnuncioService(repoMock);

        List<AnuncioExternoDTO> anunciosDTOEsperados = AnuncioExternoDTO.converte(anuncios);
        //act

        AnunciosVaziosException exceptionEsperada = assertThrows(AnunciosVaziosException.class,
                () -> anuncioService.listAnunciosByCategory(Tipos.CONGELADO)
        );

        //assert
        assertEquals(exceptionEsperada.getMessage(), "Nenhum anúncio encontrado.");
    }

    @Test
    public void deveRetornarUmAnuncioPorId() {
        //arrange
        Mockito.when(repoMock.findById(1L)).thenReturn(Optional.of(anuncio1));

        AnuncioService anuncioService = new AnuncioService(repoMock);

        AnuncioInternoDTO anuncioDTOEsperado = AnuncioInternoDTO.converte(anuncio1);
        //act

        AnuncioInternoDTO anuncioDTOObtido = anuncioService.getAnuncioById(1L);

        //assert
        assertTrue(anuncioDTOEsperado.getId().equals(anuncioDTOObtido.getId()));
    }

    @Test
    public void deveRetornarUmaExceptionQuandoNaoEncontrarUmAnuncioPorId() {
        //arrange
        Mockito.when(repoMock.findById(2L)).thenReturn(Optional.empty());

        AnuncioService anuncioService = new AnuncioService(repoMock);

        //act
        AnunciosVaziosException exceptionEsperada = assertThrows(AnunciosVaziosException.class,
                () -> anuncioService.getAnuncioById(2L)
        );

        //assert
        assertEquals(exceptionEsperada.getMessage(), "Nenhum anúncio encontrado.");
    }

    @Test
    public void deveRetornarUmAnuncioPorIdComEstoquesOrdenadosPorQuantidadeAtual() {
        //arrange
        Mockito.when(repoMock.findById(1L)).thenReturn(Optional.of(anuncio1));

        AnuncioService anuncioService = new AnuncioService(repoMock);

//        AnuncioInternoDTO anuncioDTOEsperado = AnuncioInternoDTO.converte(anuncio1);

        //act
        AnuncioInternoDTO anuncioDTOObtido = anuncioService.getAnuncioByIdOrdered(1L, "C");

        //assert
        // Ao ordenar por quantidade atual, o estoque2 deve aparecer como o primeiro da lista de estoques
        assertTrue(anuncioDTOObtido.getEstoques().get(0).getId() == 2L);
    }

    @Test
    public void deveRetornarUmAnuncioPorIdComEstoquesOrdenadosPorLote() {
        //arrange
        Mockito.when(repoMock.findById(1L)).thenReturn(Optional.of(anuncio1));

        AnuncioService anuncioService = new AnuncioService(repoMock);

//        AnuncioInternoDTO anuncioDTOEsperado = AnuncioInternoDTO.converte(anuncio1);

        //act
        AnuncioInternoDTO anuncioDTOObtido = anuncioService.getAnuncioByIdOrdered(1L, "L");

        //assert
        // Ao ordenar por quantidade atual, o estoque2 deve aparecer como o primeiro da lista de estoques
        assertTrue(anuncioDTOObtido.getEstoques().get(0).getId() == 1L);
    }

    @Test
    public void deveRetornarUmAnuncioPorIdComEstoquesOrdenadosPorDataDeVenvimento() {
        //arrange
        Mockito.when(repoMock.findById(1L)).thenReturn(Optional.of(anuncio1));

        AnuncioService anuncioService = new AnuncioService(repoMock);

//        AnuncioInternoDTO anuncioDTOEsperado = AnuncioInternoDTO.converte(anuncio1);

        //act
        AnuncioInternoDTO anuncioDTOObtido = anuncioService.getAnuncioByIdOrdered(1L, "F");

        //assert
        // Ao ordenar por quantidade atual, o estoque2 deve aparecer como o primeiro da lista de estoques
        assertTrue(anuncioDTOObtido.getEstoques().get(0).getId() == 2L);
    }

    @Test
    public void deveRetornarUmaExceptionQuandoNaoEncontrarUmAnuncioPorIdComEstoquesOrdenados() {
        //arrange
        Mockito.when(repoMock.findById(2L)).thenReturn(Optional.empty());

        AnuncioService anuncioService = new AnuncioService(repoMock);

        //act
        AnunciosVaziosException exceptionEsperada = assertThrows(AnunciosVaziosException.class,
                () -> anuncioService.getAnuncioByIdOrdered(2L, "C")
        );

        //assert
        assertEquals(exceptionEsperada.getMessage(), "Nenhum anúncio encontrado.");
    }

}
