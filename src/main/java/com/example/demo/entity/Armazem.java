package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cep;

    @OneToMany(mappedBy="armazem")
    @JsonIgnoreProperties("armazem")
    private Set<Setor> setors;

    @OneToMany(mappedBy="armazem")
    @JsonIgnoreProperties("armazem")
    private Set<Representante> representantes;
}
