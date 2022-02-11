package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="anuncio_id", nullable=false)
    private Anuncio anuncio;
    @ManyToOne
    @JoinColumn(name="carrinho_id", nullable=false)
    private Carrinho carrinho;
    private Integer quantidade;
    private BigDecimal preco;
}
