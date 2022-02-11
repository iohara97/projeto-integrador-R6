package com.example.demo.dto;

import com.example.demo.entity.*;
import com.example.demo.enums.Tipos;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
public class AnuncioInternoDTO {
    private Long id;
    private BigDecimal preco; // em R$
    private Double volume; // em m3
    private Double temperaturaMaxima;
    private Double temperaturaMinima;
    private Tipos tipo; // ENUM  (FRESCO  (0, "Fresco"); REFRIGERADO (1, "Refrigerado"), 2, "Congelado"),
    private Produto produto;
    private Vendedor vendedor;
    private List<EstoqueInternoDTO> estoques;

    public static AnuncioInternoDTO converte (Anuncio a) {
        List<Estoque> estoques = new ArrayList<Estoque>(a.getEstoques());
        List<EstoqueInternoDTO> estoquesDTO = estoques.stream().map(estoque -> {
            return EstoqueInternoDTO.converte(estoque);
        }).collect(Collectors.toList());
        return new AnuncioInternoDTO().builder()
                .id(a.getId())
                .produto(a.getProduto())
                .vendedor(a.getVendedor())
                .preco(a.getPreco())
                .volume(a.getVolume())
                .temperaturaMaxima(a.getTemperaturaMaxima())
                .temperaturaMinima(a.getTemperaturaMinima())
                .tipo(a.getTipo())
                .estoques(estoquesDTO)
                .build();
    }

}
