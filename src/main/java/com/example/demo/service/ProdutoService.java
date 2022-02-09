package com.example.demo.service;

import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(Produto produto){
        return this.produtoRepository.save(produto);
    }

}
