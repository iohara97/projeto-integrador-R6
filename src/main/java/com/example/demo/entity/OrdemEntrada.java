package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name="setor_id")
    @JsonIgnoreProperties("ordemEntradas")
    private Setor setor;

    @OneToMany(mappedBy="ordemEntrada")
    @ToString.Exclude
//    @JsonManagedReference
    @JsonIgnoreProperties({"ordemEntrada"})
    private Set<Estoque> estoques;
}
