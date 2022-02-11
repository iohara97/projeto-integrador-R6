package com.example.demo.bean;


public class VolumeEstoqueAtualizado {

    private String nome;
    private Double volume;

    public VolumeEstoqueAtualizado(String nome, Double volume) {
        this.nome = nome;
        this.volume = volume;
    }

    public String getNome() {
        return nome;
    }

    public Double getVolume() {
        return volume;
    }

}
