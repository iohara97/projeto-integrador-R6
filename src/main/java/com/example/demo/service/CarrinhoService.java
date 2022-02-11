package com.example.demo.service;

import com.example.demo.dto.CarrinhoDTO;
import com.example.demo.dto.ItemCarrinhoDTO;
import com.example.demo.dto.PrecoTotalDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Classe que contém a lógica de negócio da classe Carrinho
 */
@Service
public class CarrinhoService {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    CompradorRepository compradorRepository;

    @Autowired
    AnuncioRepository anuncioRepository;

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    public CarrinhoService(CarrinhoRepository mockCarrinho, AnuncioRepository mockAnuncio, CompradorRepository mockComprador,
                           EstoqueRepository mockEstoque, ItemCarrinhoRepository mockItemCarrinho) {
        this.carrinhoRepository = mockCarrinho;
        this.anuncioRepository = mockAnuncio;
        this.compradorRepository = mockComprador;
        this.estoqueRepository = mockEstoque;
        this.itemCarrinhoRepository = mockItemCarrinho;
    }

    /**
     * Método para salvar um carrinho e exibir o preço total do carrinho
     * @param (CarrinhoDTO) carrinhoDTO
     * @return (PrecoTotalDTO) com o valor total do carrinho
     */
    public PrecoTotalDTO save(CarrinhoDTO carrinhoDTO) {

        Comprador comprador = this.compradorRepository.findById(carrinhoDTO.getComprador_id()).orElse(new Comprador());
        if(comprador.getId() == null) {
            throw new RuntimeException("Comprador não localizado. Verifique o id do comprador.");
        }

        // Faz validação da quantidade de estoque e data de validade
        if(!checkStockAndExpirationDate(carrinhoDTO)) {
            throw new RuntimeException("Estoque insuficiente ou validade a expirar");
        }

        // Salva o Carrinho no banco de dados
        Carrinho carrinho = CarrinhoDTO.converte(carrinhoDTO, comprador);
        Carrinho carrinhoSalvo = this.carrinhoRepository.save(carrinho);

        // Salva o ItemCarrinho no banco de dados

        Set<ItemCarrinhoDTO> listaItemCarrinho = carrinhoDTO.getListaAnuncio();
        listaItemCarrinho.forEach((item -> {
            Anuncio anuncio = this.anuncioRepository.findById(item.getAnuncio_id()).orElse(new Anuncio());
            ItemCarrinho itemCarrinho = ItemCarrinhoDTO.converte(item, anuncio, carrinhoSalvo);
            decreaseStock(item.getAnuncio_id(), item.getQuantidade());
            this.itemCarrinhoRepository.save(itemCarrinho);
        }));

        // Instanciar precoTotalDTO e devolver pro controller
        return PrecoTotalDTO.builder()
                .precoTotal(this.itemCarrinhoRepository.totalPreco(carrinhoSalvo.getId()))
                .build();
    }

    /**
     * Método para buscar um carrinho pelo ID
     * @param (Long) id
     * @return (CarrinhoDTO) carrinho
     */
    public CarrinhoDTO getCarrinhoById(Long id) {

        // Retorna Carrinho pelo id
        Carrinho carrinho = this.carrinhoRepository.findById(id).orElse(new Carrinho());
        if( carrinho.getId() == null) { throw new RuntimeException("Carrinho com id " + id + " não existe."); }

        return CarrinhoDTO.converte(carrinho);
    }

    /**
     * Método para atualizar um carrinho de compra
     * @param (CarrinhoDTO) carrinhoDTO
     * @param (Long) id
     * @return (CarrinhoDTO) carrinhoSalvo
     */
    public CarrinhoDTO update(CarrinhoDTO carrinhoDTO, Long id) {

        if(this.compradorRepository.findById(carrinhoDTO.getComprador_id()).orElse(new Comprador()).getId() == null) {
            throw new RuntimeException("Comprador não localizado. Verifique o id do comprador.");
        }

        // Faz validação da quantidade de estoque e data de validade
        if(!checkStockAndExpirationDate(carrinhoDTO)) {
            throw new RuntimeException("Estoque insuficiente ou validade a expirar");
        }

        // Atualiza o Carrinho no banco de dados
        Carrinho carrinho = this.carrinhoRepository.findById(id).orElse(new Carrinho());
        if(carrinho.getId() == null) { throw new RuntimeException("Carrinho não localizado. Verifique o id do carrinho."); }

        Carrinho carrinhoSalvo = this.carrinhoRepository.saveAndFlush(carrinho);

        // Atualiza o ItemCarrinho no banco de dados
        Set<ItemCarrinhoDTO> listaItemCarrinho = carrinhoDTO.getListaAnuncio();
        listaItemCarrinho.forEach((item -> {
            ItemCarrinho itemCarrinho = this.itemCarrinhoRepository.findById(item.getId()).orElse(new ItemCarrinho());
            if(itemCarrinho.getId() == null) { throw new RuntimeException("Item com id " + item.getId() + " não existe."); }

            Anuncio anuncio = this.anuncioRepository.findById(item.getAnuncio_id()).orElse(new Anuncio());
            itemCarrinho.setCarrinho(carrinhoSalvo);
            itemCarrinho.setAnuncio(anuncio);
            decreaseStock(item.getAnuncio_id(), item.getQuantidade());
            this.itemCarrinhoRepository.saveAndFlush(ItemCarrinhoDTO.converte(item, anuncio, carrinhoSalvo));
        }));
        return CarrinhoDTO.converte(carrinhoSalvo);
    }

    /**
     * Método para validar se existe anúncio que atendem aos requisitos: quantidade maior que zero
     * e validade igual ou maior do que 21 dias
     * @param (CarrinhoDTO) dto
     * @return (Boolean) true
     */
    private Boolean checkStockAndExpirationDate(CarrinhoDTO dto) {
        // Flag para verificar se existe anúncio que atenda a esses requisitos
        for (ItemCarrinhoDTO item : dto.getListaAnuncio()) {
            if (anuncioRepository.findById(item.getAnuncio_id()).orElse(new Anuncio()).getId() == null ) {
                throw new RuntimeException("Anúncio com id " + item.getAnuncio_id() + " não existe.");
            }
            Long idResultante = this.anuncioRepository.findAnuncioByIdStockAndDateValid(item.getAnuncio_id(), item.getQuantidade());
            if(idResultante == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método privado para decrementar o estoque
     * @param (Long) anuncio_id
     * @param (Integer) quantidade
     */
    private void decreaseStock(Long anuncio_id, Integer quantidade) {
        // Decrementar estoque
        LocalDate dataValidade = LocalDate.now().plusDays(21);
        List<Estoque> listaEstoque = this.estoqueRepository.findAllAnuncio(anuncio_id);
        listaEstoque.sort(Comparator.comparing(Estoque::getDataValidade));
        listaEstoque.forEach((estoque -> {
            if(estoque.getDataValidade().compareTo(dataValidade) >= 0 && estoque.getQuantidadeAtual() >= quantidade){
                Integer quantidadeDecrementada = estoque.getQuantidadeAtual() - quantidade;
                estoque.setQuantidadeAtual(quantidadeDecrementada);
                this.estoqueRepository.save(estoque);
            }
        }));
    }
}

