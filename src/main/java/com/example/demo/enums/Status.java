package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    ABERTO (0, "Aberto"),
    FINALIZADO (1, "Finalizado");

    private int codigo;
    private String descricao;

    public static Status toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Status s: Status.values()) {
            if (codigo.equals(s.getCodigo())) {
                return s;
            }
        }

        throw new IllegalArgumentException("Código do status inválido.");

    }
}
