package com.example.demo.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ArmazemDTO {

    private Long produto_id;

    private List<ListaArmazemDTO> listaArmazem;

    public ArmazemDTO produto_id(Long produto_id) {
        this.produto_id = produto_id;
        return this;
    }

    public ArmazemDTO listaArmazem(List<ListaArmazemDTO> listaArmazem) {
        this.listaArmazem = listaArmazem;
        return this;
    }

    public ArmazemDTO build() {
        return this;
    }

}
