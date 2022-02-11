package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Comprador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="comprador")
    @ToString.Exclude
    @JsonBackReference
    private Set<Carrinho> carrinhos;

    @OneToOne()
    @ToString.Exclude
    @JsonBackReference
    private Usuario usuario;
}
