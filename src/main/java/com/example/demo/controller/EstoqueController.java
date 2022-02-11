package com.example.demo.controller;

import com.example.demo.dto.EstoqueSetorIdDataValidadeDTO;
import com.example.demo.entity.Estoque;
import com.example.demo.enums.Tipos;
import com.example.demo.interfaces.CapacidadeSetor;
import com.example.demo.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @GetMapping("")
    public ResponseEntity<List<EstoqueSetorIdDataValidadeDTO>> getEstoqueBySetorDataValidade(@Valid @RequestParam Long setorId, @RequestParam Long numeroDias) {
        List<EstoqueSetorIdDataValidadeDTO> listaEstoque = estoqueService.getAllEstoqueBySetorIdDataValidade(setorId, numeroDias);
        return ResponseEntity.status(HttpStatus.OK).body(listaEstoque);
    }

    //@RequestParam("querytype") Tipos categoria
    @GetMapping("/list")
    public ResponseEntity<List<EstoqueSetorIdDataValidadeDTO>> getEstoqueByDataValidadeCategoria(@Valid @RequestParam Long numeroDias, @RequestParam int categoria, @RequestParam String order) {
        List<EstoqueSetorIdDataValidadeDTO> listaEstoque = estoqueService.getEstoqueByDataValidadeCategoria(numeroDias, categoria, order);
        return ResponseEntity.status(HttpStatus.OK).body(listaEstoque);
    }
}
