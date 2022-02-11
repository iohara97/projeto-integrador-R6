package com.example.demo.dto;

import com.example.demo.entity.Estoque;
import com.example.demo.entity.Representante;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosParaVencerDTO {

    private Long anuncio_id;
    private String produto;
    private String vendedor;
    private String categoria;
    private Integer quantidade_atual;
    private Double temperatura_atual;
    private LocalDate data_validade;
    private String armazem;

    public static ProdutosParaVencerDTO converte(Estoque e) {

        return ProdutosParaVencerDTO.builder()
                .anuncio_id(e.getAnuncio().getId())
                .produto(e.getAnuncio().getProduto().getNome())
                .vendedor(e.getAnuncio().getVendedor().getNome())
                .categoria(e.getAnuncio().getTipo().getDescricao())
                .quantidade_atual(e.getQuantidadeAtual())
                .temperatura_atual(e.getTemperaturaAtual())
                .data_validade(e.getDataValidade())
                .armazem(e.getOrdemEntrada().getSetor().getArmazem().getNome())
                .build();
    }
}
