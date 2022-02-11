package com.example.demo.controller;

import com.example.demo.dto.RelatorioEstoqueDTO;
import com.example.demo.service.RelatorioEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/api/v1/stock-report")
public class RelatorioEstoqueController {

    @Autowired
    RelatorioEstoqueService relatorioEstoqueService;

    @GetMapping("")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoque(@RequestParam @Min(5) Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoque(estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }

    @GetMapping("/filterCategory")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoquePorCategoria(@RequestParam @NotBlank(message = "Insira uma categoria.") String categoria, @Min(5) Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoquePorCategoria(categoria, estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }

    @GetMapping("/filterSector")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoquePorSetor(@RequestParam @Min(1) Long setorId, @Min(5) Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoquePorSetor(setorId, estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }

    @GetMapping("/filterStorage")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoqueArmazem(@RequestParam @Min(1) Long armazemId, @Min(5) Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoquePorArmazem(armazemId, estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }
}
