package com.example.demo.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ListaArmazemDTO {

    private Long armazem_id;

    private Integer totalQuantidade;

    public ListaArmazemDTO armazem_id(Long armazem_id) {
        this.armazem_id = armazem_id;
        return this;
    }

    public ListaArmazemDTO totalQuantidade(Integer totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
        return this;
    }

    public ListaArmazemDTO build() {
        return this;
    }

}
