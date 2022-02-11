package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="username")
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name="perfil_id")
    private Perfil perfil;
}
