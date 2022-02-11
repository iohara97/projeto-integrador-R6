package com.example.demo.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Getter
@Setter
public class Perfil implements GrantedAuthority{

    private static final long serialVersionUID = 7497401423570420955L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;


    @OneToMany(mappedBy = "perfil")
    private List<PerfilUsuario> perfisUsuarios;


    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}