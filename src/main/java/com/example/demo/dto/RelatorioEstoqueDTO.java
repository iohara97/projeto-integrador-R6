package com.example.demo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioEstoqueDTO {

    private List<ProdutosEstoqueBaixoDTO> produtosEstoqueBaixo = new ArrayList<>();
    private Integer totalDeProdutosEstoqueBaixo;
    private List<ProdutosParaVencerDTO> produtosParaVencer = new ArrayList<>();
    private Integer totalDeProdutosAVencer;

}
