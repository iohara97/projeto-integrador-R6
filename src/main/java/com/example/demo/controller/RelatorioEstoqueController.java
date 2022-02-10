package com.example.demo.controller;

import com.example.demo.dto.RelatorioEstoqueDTO;
import com.example.demo.service.RelatorioEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-report")
public class RelatorioEstoqueController {

    @Autowired
    RelatorioEstoqueService relatorioEstoqueService;

    @GetMapping("")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoque(@RequestParam Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoque(estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }

    @GetMapping("/filterCategory")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoquePorCategoria(@RequestParam String categoria, Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoquePorCategoria(categoria, estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }

    @GetMapping("/filterSector")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoquePorSetor(@RequestParam Long setorId, Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoquePorSetor(setorId, estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }

    @GetMapping("/filterStorage")
    public ResponseEntity<RelatorioEstoqueDTO> getRelatorioEstoqueArmazem(@RequestParam Long armazemId, Integer estoqueMinimo) {

        RelatorioEstoqueDTO relatorioEstoqueDTO = relatorioEstoqueService.getRelatorioEstoquePorArmazem(armazemId, estoqueMinimo);

        return ResponseEntity.ok().body(relatorioEstoqueDTO);
    }
}
