package com.example.demo.utils;

import com.example.demo.entity.*;
import com.example.demo.enums.Tipos;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MockEstoque {

    private List<Estoque> listaEstoqueFiltrada = new ArrayList<>();
    private LocalDate dataValidade;
    private long numeroDias;

    public MockEstoque() {

        List<Produto> listaProduto = new ArrayList<>();
        listaProduto.add(
                Produto.builder()
                        .id(1L)
                        .nome("Maçã")
                        .descricao("Fruta")
                        .build()
        );

        listaProduto.add(
                Produto.builder()
                        .id(2L)
                        .nome("Iogurte")
                        .descricao("Laticínio")
                        .build()
        );

        listaProduto.add(
                Produto.builder()
                        .id(3L)
                        .nome("Açaí")
                        .descricao("Fruta congelada")
                        .build()
        );

        List<Vendedor> listaVendedor = new ArrayList<>();
        listaVendedor.add(
                Vendedor.builder()
                        .id(1L)
                        .nome("Ronaldo")
                        .build()
        );

        listaVendedor.add(
                Vendedor.builder()
                        .id(2L)
                        .nome("Jessica")
                        .build()
        );


        // ANUNCIO
        // ANUNCIO 1
        // id = 1;  preco = 1.50; volume = 0.3; tipo = FRESCO; produto = 1; vendedor = 1
        List<Anuncio> listaAnuncio = new ArrayList<>();
        listaAnuncio.add(
                Anuncio.builder()
                        .id(1L)
                        .preco(new BigDecimal(1.50))
                        .volume(0.3)
                        .temperaturaMaxima(5.0)
                        .temperaturaMinima(0.0)
                        .tipo(Tipos.FRESCO)
                        .produto(listaProduto.get(0))
                        .vendedor(listaVendedor.get(0))
                        .build()
        );
        // ANUNCIO 2
        // id = 2;  preco = 30; volume = 0.5; tipo = CONGELADO; produto = 3; vendedor = 1
        listaAnuncio.add(
                Anuncio.builder()
                        .id(2L)
                        .preco(new BigDecimal(30.0))
                        .volume(0.5)
                        .temperaturaMaxima(-1.0)
                        .temperaturaMinima(-10.0)
                        .tipo(Tipos.CONGELADO)
                        .produto(listaProduto.get(2))
                        .vendedor(listaVendedor.get(0))
                        .build()
        );

        List<Setor> listaSetor = new ArrayList<>();
        listaSetor.add(
                Setor.builder()
                        .id(1L)
                        .nome(Tipos.CONGELADO.getDescricao())
                        .volume(150.0)
                        .build()
        );
        listaSetor.add(
                Setor.builder()
                        .id(2L)
                        .nome(Tipos.REFRIGERADO.getDescricao())
                        .volume(200.0)
                        .build()
        );
        listaSetor.add(
                Setor.builder()
                        .id(3L)
                        .nome(Tipos.FRESCO.getDescricao())
                        .volume(300.0)
                        .build()
        );

        List<OrdemEntrada> listaOrdemEntrada = new ArrayList<>();
        listaOrdemEntrada.add(
                OrdemEntrada.builder()
                        .id(1L)
                        .dataCriacao(LocalDateTime.of(2022, Month.JANUARY,13,15,15,1))
                        .setor(listaSetor.get(0))
                        .build()
        );
        listaOrdemEntrada.add(
                OrdemEntrada.builder()
                        .id(2L)
                        .dataCriacao(LocalDateTime.of(2022, Month.FEBRUARY,5,10,10,1))
                        .setor(listaSetor.get(2))
                        .build()
        );

        List<Estoque> listaEstoque = new ArrayList<>();

        listaEstoque.add(
                Estoque.builder()
                        .id(1L)
                        .anuncio(listaAnuncio.get(0))
                        .ordemEntrada(listaOrdemEntrada.get(1))
                        .quantidadeInicial(100)
                        .quantidadeAtual(100)
                        .temperaturaAtual(8.0)
                        .dataValidade(LocalDate.of(2022, 2,28))
                        .dataProducao(LocalDateTime.of(2022, Month.JANUARY, 10,8,40))
                        .build()
        );

        listaEstoque.add(
                Estoque.builder()
                        .id(1L)
                        .anuncio(listaAnuncio.get(1))
                        .ordemEntrada(listaOrdemEntrada.get(0))
                        .quantidadeInicial(150)
                        .quantidadeAtual(150)
                        .temperaturaAtual(-12.0)
                        .dataValidade(LocalDate.of(2022, 3,28))
                        .dataProducao(LocalDateTime.of(2022, Month.FEBRUARY, 23,11,40))
                        .build()
        );

        List<Representante> listaRepresentante = new ArrayList<>();
        listaRepresentante.add(
                Representante.builder()
                        .id(1L)
                        .nome("Antonio")
                        .build()
        );

        List<Armazem> listaArmazem = new ArrayList<>();
        listaArmazem.add(
                Armazem.builder()
                        .id(1L)
                        .nome("CD - Sao Paulo")
                        .cep("01545-050")
                        .build()
        );
        listaArmazem.add(
                Armazem.builder()
                        .id(2L)
                        .nome("CD - Santa Catharina")
                        .cep("01330-010")
                        .build()
        );

        numeroDias = 21;
        dataValidade =  LocalDate.now().plusDays(numeroDias);

        for(Estoque e: listaEstoque) {
            if((e.getDataValidade().isAfter(dataValidade) || e.getDataValidade().equals(dataValidade) ) && e.getOrdemEntrada().getSetor().getId() == 3){
                listaEstoqueFiltrada.add(e);
            }
        }
    }
}
