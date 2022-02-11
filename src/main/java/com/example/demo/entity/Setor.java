package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome; // CONGELADO, REFRIGERADO, FRESCO
    private Double volume;

    @ManyToOne
    @JoinColumn(name="armazem_id") // insertable = false, updatable = false)
    @JsonIgnoreProperties("setors")
    private Armazem armazem;

    @OneToMany(mappedBy="setor")
    @JsonIgnoreProperties(value = {"setor", "anuncio"})
    private Set<OrdemEntrada> ordemEntradas;

}

