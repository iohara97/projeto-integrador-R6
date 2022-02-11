package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="comprador_id", nullable=false)
    private Comprador comprador;
    private LocalDateTime dataCriacao;
    private int status;

    @OneToMany(mappedBy="carrinho")
    @ToString.Exclude
    @JsonBackReference
    private Set<ItemCarrinho> itensCarrinho;
}
