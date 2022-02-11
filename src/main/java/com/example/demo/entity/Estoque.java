package com.example.demo.entity;

import com.example.demo.utils.View;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.annotation.security.PermitAll;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
//    @JsonManagedReference
    @JsonIgnoreProperties("estoques")
    @JoinColumn(name="anuncio_id")
    private Anuncio anuncio;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
//    @JsonBackReference
    @JsonIgnoreProperties({"estoques"})
    @JoinColumn(name="ordem_entrada_id")
    @JsonView({View.Representante.class, View.Admin.class})
    private OrdemEntrada ordemEntrada;


    private Integer quantidadeInicial;
    private Integer quantidadeAtual;
    private Double temperaturaAtual;
    private LocalDate dataValidade;
    private LocalDateTime dataProducao;

    public static Estoque newEstoque(Estoque e) {
        return Estoque.builder()
                .id(e.getId())
                .anuncio(e.getAnuncio())
                .ordemEntrada(e.getOrdemEntrada())
                .quantidadeInicial(e.getQuantidadeInicial())
                .quantidadeAtual(e.getQuantidadeAtual())
                .temperaturaAtual(e.getTemperaturaAtual())
                .dataValidade(e.getDataValidade())
                .dataProducao(e.getDataProducao())
                .build();
    }
}
