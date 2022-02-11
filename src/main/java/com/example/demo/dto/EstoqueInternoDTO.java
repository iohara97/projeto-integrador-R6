package com.example.demo.dto;

import com.example.demo.entity.Anuncio;
import com.example.demo.entity.Estoque;
import com.example.demo.entity.OrdemEntrada;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
public class EstoqueInternoDTO {

    private Long id;
    private Integer quantidadeInicial;
    private Integer quantidadeAtual;
    private Double temperaturaAtual;
    private LocalDate dataValidade;
    private LocalDateTime dataProducao;

    @JsonIgnoreProperties("estoques")
    private OrdemEntrada ordemEntrada;


    public static EstoqueInternoDTO converte(Estoque e) {

//        OrdemEntradaInternoDTO ordemEntradaInternoDTO = OrdemEntradaInternoDTO.converte(e.getOrdemEntrada());
        EstoqueInternoDTO dto = EstoqueInternoDTO.builder()
                .id(e.getId())
                .quantidadeInicial(e.getQuantidadeInicial())
                .quantidadeAtual(e.getQuantidadeAtual())
                .temperaturaAtual(e.getTemperaturaAtual())
                .dataValidade(e.getDataValidade())
                .dataProducao(e.getDataProducao())
                .ordemEntrada(e.getOrdemEntrada())
                .build();
        return dto;
    }

//    public static Estoque converte(EstoqueDTO dto, Anuncio anuncio, OrdemEntrada ordemEntrada) {
//        return Estoque.builder()
//                .id(dto.getId())
//                .anuncio(anuncio)
//                .ordemEntrada(ordemEntrada)
//                .quantidadeInicial(dto.getQuantidadeInicial())
//                .quantidadeAtual(dto.getQuantidadeAtual())
//                .temperaturaAtual(dto.getTemperaturaAtual())
//                .dataValidade(dto.getDataValidade())
//                .dataProducao(dto.getDataProducao())
//                .build();
//    }
//
    public static List<EstoqueInternoDTO> converte(List<Estoque> estoque) {
        return estoque.stream().map(EstoqueInternoDTO::converte).collect(Collectors.toList());
    }
//
//    public static Set<EstoqueDTO> converte(Set<Estoque> estoque) {
//        return estoque.stream().map(EstoqueDTO::converte).collect(Collectors.toSet());
//    }
}
