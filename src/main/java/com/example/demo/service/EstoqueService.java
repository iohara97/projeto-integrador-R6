package com.example.demo.service;

import com.example.demo.dto.EstoqueSetorIdDataValidadeDTO;
import com.example.demo.entity.Estoque;
import com.example.demo.repository.EstoqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que contem a logica de negocio da entidade Estoque
 */
@Service
@AllArgsConstructor
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    /**
     * Método para obter uma lista de estoque por setor e por um numero de dias especificado na pesquisa
     * @param (Long) setorId
     * @param (Long) numeroDias
     * @return (List<EstoqueSetorIdDataValidadeDTO>) lista de estoque por setor e por numero de dias prestes a vencer
     */
    public List<EstoqueSetorIdDataValidadeDTO> getAllEstoqueBySetorIdDataValidade(Long setorId, Long numeroDias){
        LocalDate dataValidade = LocalDate.now().plusDays(numeroDias);
        return EstoqueSetorIdDataValidadeDTO.converte(this.estoqueRepository.getAnunciosBySetorValidadeRep(1L, setorId, dataValidade));
    }

    /**
     * Método para obter uma lista de estoque por categoria e por um numero de dias especificado na pesquisa
     * ordenando em ordem crescente (ASC) ou decrescente (DESC)
     * @param (Long) numeroDias
     * @param (int) categoria
     * @param (String) order (ASC ou DESC)
     * @return (List<EstoqueSetorIdDataValidadeDTO>) lista de estoque por categoria e por numero de dias prestes a vencer
     */
    public List<EstoqueSetorIdDataValidadeDTO> getEstoqueByDataValidadeCategoria(Long numeroDias, int categoria, String order) {
        LocalDate dataValidade = LocalDate.now().plusDays(numeroDias);
        List<Estoque> estoque = this.estoqueRepository.getEstoqueByDataValidadeCategoria(dataValidade, categoria);
        estoque.sort(Comparator.comparing(Estoque::getDataValidade));
        if(order.equals("desc")){
            estoque.sort(Comparator.comparing(Estoque::getDataValidade).reversed());
        }
        return EstoqueSetorIdDataValidadeDTO.converte(estoque);
    }
}

