package com.example.demo.dto;

import com.example.demo.entity.Anuncio;
import com.example.demo.entity.Estoque;
import com.example.demo.entity.Produto;
import com.example.demo.entity.Vendedor;
import com.example.demo.enums.Tipos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class AnuncioExternoDTO {
    private Long id;
    private BigDecimal preco; // em R$
    private Double volume; // em m3
    private Double temperaturaMaxima;
    private Double temperaturaMinima;
    private Tipos tipo; // ENUM  (FRESCO  (0, "Fresco"); REFRIGERADO (1, "Refrigerado"), 2, "Congelado"),
    private Produto produto;
    private Vendedor vendedor;

    @JsonIgnoreProperties({ "anuncio", "ordemEntrada"})
    private List<Estoque> estoques;

    public static AnuncioExternoDTO converte (Anuncio a) {
        List<Estoque> estoques = new ArrayList<Estoque>(a.getEstoques());
//        List<AnuncioExternoDTO> estoquesDTO = estoques.stream().map(estoque -> {
//            return AnuncioExternoDTO.converte(estoque);
//        }).collect(Collectors.toList());
        return new AnuncioExternoDTO().builder()
                .id(a.getId())
                .produto(a.getProduto())
                .vendedor(a.getVendedor())
                .preco(a.getPreco())
                .volume(a.getVolume())
                .temperaturaMaxima(a.getTemperaturaMaxima())
                .temperaturaMinima(a.getTemperaturaMinima())
                .tipo(a.getTipo())
                .estoques(estoques)
                .build();
    }

    public static List<AnuncioExternoDTO> converte (List<Anuncio> anuncios) {
        return anuncios.stream().map(AnuncioExternoDTO::converte).collect(Collectors.toList());
    }

}
