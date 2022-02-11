package com.example.demo.service;

import com.example.demo.entity.Vendedor;
import com.example.demo.repository.VendedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe que contem a logica de negocio da entidade Vendedor
 */
@AllArgsConstructor
@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    /**
     * Método para salvar um vendedor
     * @param (Vendedor) vendedor
     * @return (Vendedor) vendedor
     */
    public Vendedor save(Vendedor vendedor){
        return this.vendedorRepository.save(vendedor);
    }

}
