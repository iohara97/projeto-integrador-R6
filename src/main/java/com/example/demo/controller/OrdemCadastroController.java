package com.example.demo.controller;

import com.example.demo.dto.EstoqueDTO;
import com.example.demo.dto.InboundOrderDTO;
import com.example.demo.entity.OrdemEntrada;
import com.example.demo.interfaces.CapacidadeSetor;
import com.example.demo.service.OrdemEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class OrdemCadastroController {

    @Autowired
    private OrdemEntradaService ordemEntradaService;

    @PostMapping("/inboundorder")
    public ResponseEntity<List<EstoqueDTO>> cadastraLote(@Valid @RequestBody InboundOrderDTO inboundOrderDTO) {
        List<EstoqueDTO> listaEstoque = ordemEntradaService.save(inboundOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(listaEstoque);
    }

    @PutMapping("/inboundorder/{id}")
    public ResponseEntity<InboundOrderDTO> atualizaEstoque(@Valid @RequestBody InboundOrderDTO inboundOrderDTO, @PathVariable Long id) {
        InboundOrderDTO ordemEntradaAtualizada = ordemEntradaService.update(inboundOrderDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemEntradaAtualizada);
    }

    @GetMapping("/inboundorder/{id}")
    public ResponseEntity<OrdemEntrada> getEstoque(@PathVariable Long id) {
        OrdemEntrada ordemEntrada = ordemEntradaService.getOrdemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ordemEntrada);
    }

    @GetMapping("/capacidadeSetor/{setorId}")
    public ResponseEntity<CapacidadeSetor> getCapacidadeSetores(@Valid @PathVariable Long setorId) {
        CapacidadeSetor capacidadeSetores = ordemEntradaService.getCapacidadeSetor(setorId);
        return ResponseEntity.status(HttpStatus.OK).body(capacidadeSetores);
    }

}
