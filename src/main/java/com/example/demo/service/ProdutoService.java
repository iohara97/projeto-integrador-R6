package com.example.demo.service;

import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe que contem a logica de negocio da entidade Produto
 */
@AllArgsConstructor
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para salvar um produto
     * @param (Produto) produto
     * @return (Produto) produto
     */
    public Produto save(Produto produto){
        return this.produtoRepository.save(produto);
    }

}
