package com.example.demo.service;

import com.example.demo.dto.EstoqueDTO;
import com.example.demo.dto.InboundOrderDTO;
import com.example.demo.entity.*;
import com.example.demo.interfaces.CapacidadeSetor;
import com.example.demo.repository.*;
import exception.BusinessException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Classe que contem a logica de negocio da entidade OrdemEntrada
 */
@Service
public class OrdemEntradaService {

    @Autowired
    OrdemEntradaRepository ordemEntradaRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    SetorRepository setorRepository;

    @Autowired
    AnuncioRepository anuncioRepository;

    public OrdemEntradaService(OrdemEntradaRepository ordemEntradaRepository, EstoqueRepository estoqueRepository,
                               SetorRepository setorRepository, AnuncioRepository anuncioRepository) {
        this.ordemEntradaRepository = ordemEntradaRepository;
        this.estoqueRepository = estoqueRepository;
        this.setorRepository = setorRepository;
        this.anuncioRepository = anuncioRepository;
    }

    // ------------------ //
    // MÉTODOS PRINCIPAIS //
    // ------------------ //

    /**
     * Método para salvar uma ordem de Entrada
     * @param (InboundOrderDTO) inboundOrderDTO
     * @return (List<EstoqueDTO) estoqueCadastrado
     */
    @SneakyThrows
    public List<EstoqueDTO> save(InboundOrderDTO inboundOrderDTO){
        Setor setor = this.setorRepository.getById(inboundOrderDTO.getOrdemEntradaDTO().getSetor_id());

        //SALVA ORDEM DE ENTRADA
        OrdemEntrada ordemEntradaSalva = saveOrdemEntrada(
            OrdemEntrada.builder()
            .setor(setor)
            .dataCriacao(inboundOrderDTO.getOrdemEntradaDTO().getDataCriacao())
            .build()
        );

        // SALVO O ESTOQUE
        Set<EstoqueDTO> listaEstoque = inboundOrderDTO.getListaEstoqueDTO();
        Double capacidadeDoSetor = this.getCapacidadeSetor(ordemEntradaSalva.getSetor().getId()).getVolume();
        Double volumeTotalDaEntradaDeAnuncios = this.getVolume(listaEstoque);

        if(capacidadeDoSetor - volumeTotalDaEntradaDeAnuncios >= 0){
            saveListaEstoque(listaEstoque, ordemEntradaSalva, setor);
        } else {
            throw new RuntimeException("Setor não tem volume suficiente para alocar o estoque");
        }

        List<Estoque> estoqueCadastrado = this.estoqueRepository.findAllEstoque(ordemEntradaSalva);
        return EstoqueDTO.converte(estoqueCadastrado);
    }

    /**
     * Método para atualizar uma ordem de entrada
     * @param (InboundOrderDTO) inboundOrderDTO
     * @param (Long) id
     * @return (InboundOrderDTO) ordemEntradaSalva
     */
    @SneakyThrows
    public InboundOrderDTO update(InboundOrderDTO inboundOrderDTO, Long id) {
        OrdemEntrada ordemEntrada = this.ordemEntradaRepository.findById(id).orElse(new OrdemEntrada());
        Setor setor = this.setorRepository.findById(inboundOrderDTO.getOrdemEntradaDTO().getSetor_id()).orElse(new Setor());
        ordemEntrada.setSetor(setor);
        ordemEntrada.setDataCriacao(inboundOrderDTO.getOrdemEntradaDTO().getDataCriacao());

        //SALVA ORDEM DE ENTRADA
        OrdemEntrada ordemEntradaSalva = saveOrdemEntrada(ordemEntrada);

        // SALVO O ESTOQUE
        Set<EstoqueDTO> listaEstoque = inboundOrderDTO.getListaEstoqueDTO();
        Double capacidadeDoSetor = this.getCapacidadeSetor(ordemEntrada.getSetor().getId()).getVolume();
        Double volumeTotalDaEntradaDeAnuncios = this.getVolume(listaEstoque);

        if(capacidadeDoSetor - volumeTotalDaEntradaDeAnuncios >= 0){
            saveListaEstoque(listaEstoque, ordemEntradaSalva, setor);
        } else {
            throw new RuntimeException("Setor não tem volume suficiente para alocar o estoque");
        }

        return InboundOrderDTO.converte(ordemEntradaSalva);
    }

    /**
     * Método para obter a capacidade do setor
     * @param (Long) setorId
     * @return (CapacidadeSetor)
     */
    public CapacidadeSetor getCapacidadeSetor(Long setorId){
        return this.setorRepository.getCapacidadeSetorById(setorId);
    }


    // ----------------------- //
    // MÉTODOS DE PERSISTÊNCIA //
    // ----------------------- //

    /**
     * Método para obter uma ordem de entrada buscando pelo ID
     * @param (Long) id
     * @return (OrdemEntrada)
     */
    public OrdemEntrada getOrdemById(Long id) {
        return this.ordemEntradaRepository.findById(id).orElse(new OrdemEntrada());
    }

    /**
     * Método privado para salvar a ordem de entrada
     * @param (OrdemEntrada) ordemEntrada
     * @return (OrdemEntrada) ordemEntrada
     * @throws BusinessException em caso de não haver setor cadastrado
     */
    private OrdemEntrada saveOrdemEntrada(OrdemEntrada ordemEntrada) throws BusinessException {
        try{
            return this.ordemEntradaRepository.save(ordemEntrada);
        } catch (Exception e){
            throw new BusinessException("Setor não cadastrado.");
        }
    }

    /**
     * Método privado para salvar uma lista de estoque
     * @param (Set<EstoqueDTO>) listaEstoque
     * @param (OrdemEntrada) ordemEntrada
     * @param (Setor) setor
     */
    private void saveListaEstoque(Set<EstoqueDTO> listaEstoque, OrdemEntrada ordemEntrada, Setor setor){
        listaEstoque.forEach(estoque -> {
            saveEstoque(estoque, ordemEntrada, setor);
        });
    }

    /**
     * Método privado para salvar estoque
     * @param (EstoqueDTO) estoque
     * @param (OrdemEntrada) ordemEntrada
     * @param (Setor) setor
     */
    private void saveEstoque(EstoqueDTO estoque, OrdemEntrada ordemEntrada, Setor setor){
        Anuncio anuncio = this.anuncioRepository.findById(estoque.getAnuncio_id()).orElse(new Anuncio());

        //VALIDAR O TIPO DO PRODUTO == TIPO DO SETOR
        if (anuncio.getTipo().getDescricao().equalsIgnoreCase(setor.getNome())) {
            Estoque estoqueConvertido = EstoqueDTO.converte(estoque, anuncio, ordemEntrada);
            this.estoqueRepository.save(estoqueConvertido);
        } else {
            throw new RuntimeException("Setor inválido");
        }
    }


    // ------------------ //
    // MÉTODOS AUXILIARES //
    // ------------------ //

    /**
     * Método privado para retornar o volume total de uma lista de estoque para usar no método update
     * @param (Set<EstoqueDTO>) listaEstoque
     * @return (Double) volumeTotal
     */
    private double getVolume(Set<EstoqueDTO> listaEstoque){
        double volumeTotal = 0.0;
        for (EstoqueDTO estoque : listaEstoque) {
            Anuncio anuncio = this.anuncioRepository.getById(estoque.getAnuncio_id());
            volumeTotal += anuncio.getVolume() * estoque.getQuantidadeAtual();
        }
        return volumeTotal;
    }
}
