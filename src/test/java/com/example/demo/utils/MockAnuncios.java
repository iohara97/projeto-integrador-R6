package com.example.demo.utils;

import com.example.demo.dto.EstoqueDTO;
import com.example.demo.dto.InboundOrderDTO;
import com.example.demo.dto.OrdemEntradaDTO;
import com.example.demo.dto.RelatorioEstoqueDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.Tipos;
import com.example.demo.interfaces.CapacidadeSetor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class MockAnuncios {

    private InboundOrderDTO inboundOrderDTO;
    private OrdemEntradaDTO ordemEntradaDTO;
    private Set<EstoqueDTO> estoqueSetDTO = new HashSet<>();
    private List<Anuncio> anuncios = new ArrayList<>();
    private Setor setor1;
    private Armazem armazem1;
    private Estoque estoque1;
    private OrdemEntrada ordemEntrada1;
    private CapacidadeSetor capacidadeSetor;
    private List<Estoque> estoqueList;

    public MockAnuncios() {
        ordemEntradaDTO = OrdemEntradaDTO.builder()
                .dataCriacao(LocalDateTime.of(2022, 2, 9, 10, 18, 2))
                .setor_id(1L)
                .build();

        EstoqueDTO estoqueDTO1 = EstoqueDTO.builder()
                .anuncio_id(1L)
                .quantidadeInicial(100)
                .quantidadeAtual(100)
                .temperaturaAtual(20D)
                .dataValidade(LocalDate.of(2022, 12, 1))
                .dataProducao(LocalDateTime.of(2022, 1, 1, 12, 30))
                .build();

        EstoqueDTO estoqueDTO2 = EstoqueDTO.builder()
                .anuncio_id(2L)
                .dataProducao(LocalDateTime.of(2022, 1, 1, 12, 30))
                .dataValidade(LocalDate.of(2022, 11, 1))
                .quantidadeAtual(50)
                .quantidadeInicial(500)
                .temperaturaAtual(20D)
                .build();

        estoqueSetDTO.add(estoqueDTO1);
        estoqueSetDTO.add(estoqueDTO2);

        inboundOrderDTO = InboundOrderDTO.builder()
                .ordemEntradaDTO(ordemEntradaDTO)
                .listaEstoqueDTO(estoqueSetDTO)
                .build();

        Vendedor vendedor1 = Vendedor.builder()
                .id(1L)
                .nome("Juliana")
                .build();

        Produto produto1 = Produto.builder()
                .id(1L)
                .nome("Maçã")
                .descricao("Maçã orgânica")
                .build();

        Anuncio anuncio1 = Anuncio.builder()
                .id(1L)
                .temperaturaMaxima(25D)
                .temperaturaMinima(10D)
                .volume(0.001D)
                .preco(BigDecimal.valueOf(5.25))
                .vendedor(vendedor1)
                .produto(produto1)
                .tipo(Tipos.FRESCO)
                .build();

        Set<Anuncio> anunciosSet = new HashSet<>();
        anunciosSet.add(anuncio1);
        produto1.setAnuncios(anunciosSet);
        vendedor1.setAnuncios(anunciosSet);

        armazem1 = Armazem.builder()
                .id(1L)
                .cep("01000-001")
                .nome("CD_Guarulhos")
                .build();

        setor1 = Setor.builder()
                .id(1L)
                .armazem(armazem1)
                .volume(500D)
                .nome("FRESCO")
                .build();


        ordemEntrada1 = OrdemEntrada.builder()
                .id(1L)
                .setor(setor1)
                .dataCriacao(LocalDateTime.of(2022, 2, 9, 10, 18, 2))
                .build();

        estoque1 = Estoque.builder()
                .id(1L)
                .dataProducao(LocalDateTime.of(2022, 1, 1, 12, 30))
                .dataValidade(LocalDate.of(2022, 2, 20))
                .quantidadeAtual(100)
                .quantidadeInicial(100)
                .temperaturaAtual(20D)
                .anuncio(anuncio1)
                .ordemEntrada(ordemEntrada1)
                .build();

        Estoque estoque2 = Estoque.builder()
                .id(2L)
                .dataProducao(LocalDateTime.of(2022, 1, 1, 12, 30))
                .dataValidade(LocalDate.of(2022, 11, 1))
                .quantidadeAtual(50)
                .quantidadeInicial(500)
                .temperaturaAtual(20D)
                .anuncio(anuncio1)
                .ordemEntrada(ordemEntrada1)
                .build();

        Set<Estoque> estoqueSet = new HashSet<>();
        estoqueSet.add(estoque1);
        estoqueSet.add(estoque2);

        ordemEntrada1.setEstoques(estoqueSet);

        anuncio1.setEstoques(estoqueSet);

        this.anuncios.add(anuncio1);

        /* Testando para o setor 1.
           Algoritmo da query getCapacidadeSetorById simulado.
         */
        List<Double> capacidadeTotal = new ArrayList<>();

        for (Estoque e: estoqueSet) {
            if(e.getOrdemEntrada().getId().equals(setor1.getId())) {
                capacidadeTotal.add(setor1.getVolume() - (e.getQuantidadeAtual() * e.getAnuncio().getVolume()));
            }
        }

        Double volumeTotal = capacidadeTotal.stream().reduce(0D,Double::sum);

        // Valores setados na interface que a query retorna

        capacidadeSetor = new CapacidadeSetor() {
            @Override
            public String getNome() {
                return setor1.getNome();
            }

            @Override
            public Double getVolume() {
                return volumeTotal;
            }
        };

        estoqueList = new ArrayList<>();

        /* Testando para o ordem entrada 1.
           Algoritmo da query findAllEstoque simulado.
         */
        for (Estoque e: estoqueSet) {
            if(e.getOrdemEntrada().getId() == ordemEntrada1.getId() ) {
                estoqueList.add(e);
            }
        }
    }
}
