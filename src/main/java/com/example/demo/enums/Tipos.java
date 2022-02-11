package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tipos {

    FRESCO (0, "Fresco"),
    REFRIGERADO (1, "Refrigerado"),
    CONGELADO (2, "Congelado");

    private int codigo;
    private String descricao;

    public static Tipos toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Tipos t: Tipos.values()) {
            if (codigo.equals(t.getCodigo())) {
                return t;
            }
        }

        throw new IllegalArgumentException("Id do Enum inválido. Código do tipo do Setor inválido.");

    }


}
