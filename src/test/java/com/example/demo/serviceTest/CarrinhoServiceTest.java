package com.example.demo.serviceTest;

import com.example.demo.dto.CarrinhoDTO;
import com.example.demo.dto.ItemCarrinhoDTO;
import com.example.demo.dto.PrecoTotalDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CarrinhoService;
import com.example.demo.utils.MockCarrinho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class CarrinhoServiceTest {

    private MockCarrinho mockCarrinho;
    private Carrinho carrinho;
    private Carrinho carrinhoSalvo;
    private List<ItemCarrinho> itensCarrinhos;
    private Comprador comprador;
    private ItemCarrinho itemCarrinho;
    private ItemCarrinho itemCarrinhoUpdate;
    private List<Estoque> listaEstoque;
    private ItemCarrinhoDTO item;
    private Anuncio anuncio;
    private CarrinhoDTO dto;
    private CarrinhoDTO dtoUpdate;
    private ItemCarrinhoRepository mockItemCarrinho;
    private CarrinhoService carrinhoService;

    @BeforeEach
    public void init() {

        mockCarrinho = new MockCarrinho();
        carrinho = mockCarrinho.getCarrinho();
        carrinhoSalvo = mockCarrinho.getCarrinhoSalvo();
        itensCarrinhos = mockCarrinho.getItensCarrinhos();
        comprador = mockCarrinho.getComprador();
        itemCarrinho = mockCarrinho.getItemCarrinho();
        itemCarrinhoUpdate = mockCarrinho.getItemCarrinhoUpdate();
        listaEstoque = mockCarrinho.getListaEstoque();
        item = mockCarrinho.getItem();
        anuncio = mockCarrinho.getAnuncio();
        dto = mockCarrinho.getDto();
        dtoUpdate = mockCarrinho.getDtoUpdate();

        CarrinhoRepository mockCarrinho = Mockito.mock(CarrinhoRepository.class);
        AnuncioRepository mockAnuncio = Mockito.mock(AnuncioRepository.class);
        CompradorRepository mockComprador = Mockito.mock(CompradorRepository.class);
        EstoqueRepository mockEstoque = Mockito.mock(EstoqueRepository.class);
        mockItemCarrinho = Mockito.mock(ItemCarrinhoRepository.class);

        Mockito.when(mockCarrinho.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);
        Mockito.when(mockCarrinho.saveAndFlush(Mockito.any(Carrinho.class))).thenReturn(carrinhoSalvo);
        Mockito.when(mockCarrinho.findById(1L)).thenReturn(Optional.of(carrinho));
        Mockito.when(mockComprador.save(comprador)).thenReturn(comprador);
        Mockito.when(mockComprador.save(comprador)).thenReturn(comprador);
        Mockito.when(mockComprador.findById(comprador.getId())).thenReturn(Optional.of(comprador));
        Mockito.when(mockItemCarrinho.save(itemCarrinho)).thenReturn(itemCarrinho);
        Mockito.when(mockItemCarrinho.saveAndFlush(itemCarrinho)).thenReturn(itemCarrinhoUpdate);
        Mockito.when(mockItemCarrinho.findById(item.getId())).thenReturn(Optional.of(itemCarrinho));
        Mockito.when(mockEstoque.findAllAnuncio(item.getAnuncio_id())).thenReturn(listaEstoque);
        Mockito.when(mockAnuncio.findById(item.getAnuncio_id())).thenReturn(Optional.of(anuncio));

        carrinhoService = new CarrinhoService(mockCarrinho, mockAnuncio, mockComprador, mockEstoque, mockItemCarrinho);
    }

    @Test
    public void deveSalvarUmCarrinho() {

        // Simular comportamento da query precoTotal em ItemCarrinhoRepository
        List<Double> listaPrecos = new ArrayList<>();

        for (ItemCarrinho i : itensCarrinhos) {
            Double preco = i.getPreco().doubleValue() * i.getQuantidade();
            listaPrecos.add(preco);
        }
        Double totalPreco = listaPrecos.stream().reduce(0d,Double::sum);

        Mockito.when(mockItemCarrinho.totalPreco(carrinho.getId())).thenReturn(totalPreco);

        PrecoTotalDTO precoTotalDTO = carrinhoService.save(dto);
        assertEquals(precoTotalDTO.getPrecoTotal(), 2);
    }

    @Test
    public void deveAtualizarQuantidadeDeUmCarrinho() {

        CarrinhoDTO carrinhoDTOAtual = carrinhoService.update(dtoUpdate, 1L);

        /* Verificar se o carrinho foi atualizado comparando ao carrinho original do MockCarrinho.
           O For foi feito para percorrer as listas e popular dentro de uma ArrayList as quantidades do dto original e dto atualizado para
           compara-los no assert.
         */

        List<CarrinhoDTO> listaDTO = new ArrayList<>();
        List<Integer> listaQuantidade = new ArrayList<>();
        listaDTO.add(dto);
        listaDTO.add(carrinhoDTOAtual);

        for (CarrinhoDTO c : listaDTO) {
            c.getListaAnuncio().forEach(a -> {
                listaQuantidade.add(a.getQuantidade());
            });
        }

        assertNotSame(listaQuantidade.get(0), listaQuantidade.get(1));
    }
}
