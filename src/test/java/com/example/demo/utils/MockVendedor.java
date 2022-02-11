package com.example.demo.utils;

import com.example.demo.entity.Vendedor;
import lombok.Data;

@Data
public class MockVendedor {

    private Vendedor vendedor;

    public MockVendedor(){

        vendedor = Vendedor.builder()
                .id(1L)
                .nome("Marcos")
                .build(); 
    }
}
