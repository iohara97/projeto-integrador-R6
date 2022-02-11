package com.example.demo.utils;

import com.example.demo.entity.Produto;
import lombok.Data;

@Data
public class MockProduto {

    private Produto produto;

    public MockProduto() {

        produto = Produto.builder()
                .id(1L)
                .nome("alface")
                .descricao("verde")
                .build();
    }

}
