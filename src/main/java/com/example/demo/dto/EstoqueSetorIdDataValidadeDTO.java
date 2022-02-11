package com.example.demo.dto;


import com.example.demo.entity.Estoque;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueSetorIdDataValidadeDTO {
    private Long estoqueId;
    private Long anuncioId;
    private String tipoProduto;
    private LocalDate dataValidade;
    private Integer quantidade;

    public static EstoqueSetorIdDataValidadeDTO converte(Estoque e){
        return EstoqueSetorIdDataValidadeDTO.builder()
            .estoqueId(e.getId())
            .anuncioId(e.getAnuncio().getId())
            .tipoProduto(e.getAnuncio().getTipo().getDescricao())
            .dataValidade(e.getDataValidade())
            .quantidade(e.getQuantidadeAtual())
            .build();
    }

    public static List<EstoqueSetorIdDataValidadeDTO> converte(List<Estoque> e){
        return e.stream().map(EstoqueSetorIdDataValidadeDTO::converte).collect(Collectors.toList());
    }
}
