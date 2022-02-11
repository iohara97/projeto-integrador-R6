package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Usuario implements UserDetails{

    private static final long serialVersionUID = 1L;



    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    @Id
    @Column(name="username")
    private String user;
    @Column(name="password")
    private String senha;
    @Column(name="enabled")
    private boolean ativo;

    //@ManyToMany(fetch = FetchType.EAGER)
    //private List<Perfil> perfis;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
    private List<PerfilUsuario> perfisUsuarios;


    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.perfisUsuarios.forEach(perfil-> {
            SimpleGrantedAuthority autority = new SimpleGrantedAuthority(perfil.getPerfil().getNome());
            authorities.add(autority);
            System.out.println("\n\n\n*******\nAUTORITY: " + autority);
            System.out.println("\nPERFIL: " + perfil.getPerfil().getNome());
        });
        //this.perfis.forEach(perfil-> authorities.add(new SimpleGrantedAuthority(perfil.getNome())));
        return authorities;
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.senha;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.user;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


}