package com.example.demo.service;

import com.example.demo.dto.AnuncioExternoDTO;
import com.example.demo.dto.AnuncioInternoDTO;
import com.example.demo.dto.EstoqueInternoDTO;
import com.example.demo.entity.Anuncio;
import com.example.demo.entity.Estoque;
import com.example.demo.enums.Tipos;
import com.example.demo.exception.AnunciosVaziosException;
import com.example.demo.repository.AnuncioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

/**
 * Classe que contem a lógica de negócio da classe Anuncio
 */
@Service
public class AnuncioService {

    private AnuncioRepository anuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    /** Método para retornar uma lista de anúncios validos
     * com quantidade atual maior do que zero e validade de pelos menos 3 semanas
     * @return (List<AnuncioExternoDTO>) lista de anunciosDTO personalizado com as saídas definidas para o comprador
     */
    public List<AnuncioExternoDTO> listAnunciosValidos() {

        LocalDate dataValidadeMais3Semanas = LocalDate.now().plusWeeks(3);
        List<Anuncio> anuncios = this.anuncioRepository.findAllAnunciosWithStockAndDueDateValid(dataValidadeMais3Semanas);
        if (anuncios.isEmpty()) throw new AnunciosVaziosException("Nenhum anúncio encontrado.");
        List<AnuncioExternoDTO> anunciosDTO = AnuncioExternoDTO.converte(anuncios);
        return anunciosDTO;
    }

    /**
     * Métodos para listar todos os anúncios válidos com quantidade atual maior que zero
     * e validade de pelos menos 3 semanas, por categoria
     * @param (Tipos) categoria (FRESCO, REFRIGERADO ou CONGELADO)
     * @return (List<AnuncioExternoDTO>) lista de anunciosDTO personalizado com as saídas definidas para o comprador
     */
    public List<AnuncioExternoDTO> listAnunciosByCategory(Tipos categoria) {

        LocalDate dataValidadeMais3Semanas = LocalDate.now().plusWeeks(3);
        List<Anuncio> anuncios = this.anuncioRepository.findAllAnunciosByCategoryWithStockAndDueDateValid(dataValidadeMais3Semanas, categoria);
        if (anuncios.isEmpty()) throw new AnunciosVaziosException("Nenhum anúncio encontrado.");
        List<AnuncioExternoDTO> anunciosDTO = AnuncioExternoDTO.converte(anuncios);
        return anunciosDTO;
    }

    /**
     * Método para listar um anúncio pelo ID
     * @param (Long) anuncioId
     * @return (anuncioInternoDTO) anúncio personalizado com as saídas definidas para o vendedor
     */
    public AnuncioInternoDTO getAnuncioById(Long anuncioId) {

        Optional<Anuncio> anuncioOptional = this.anuncioRepository.findById(anuncioId);
        if (anuncioOptional.isEmpty()) throw new AnunciosVaziosException("Nenhum anúncio encontrado.");
        Anuncio anuncio = anuncioOptional.get();
        AnuncioInternoDTO anuncioInternoDTO = AnuncioInternoDTO.converte(anuncio);
        return anuncioInternoDTO;
    }

    /**
     * Método para listar um anúncio pelo ID, ordenando pelos estoques
     * @param (Long) anuncioId
     * @param (String) orderBy
     * @return (anuncioInternoDTO) anúncio personalizado com as saídas definidas para o vendedor
     */
    public AnuncioInternoDTO getAnuncioByIdOrdered(Long anuncioId, String orderBy) {

        Optional<Anuncio> anuncioOptional = this.anuncioRepository.findById(anuncioId);
        if (anuncioOptional.isEmpty()) throw new AnunciosVaziosException("Nenhum anúncio encontrado.");
        Anuncio anuncio = anuncioOptional.get();

        // Ordena os estoques
        AnuncioInternoDTO anuncioInternoDTO = AnuncioInternoDTO.converte(anuncio);

        List<Estoque> estoques = getEstoquesOrdenados(anuncio.getEstoques(), orderBy);

        // Converte para DTO
        List<EstoqueInternoDTO> estoquesDTO = EstoqueInternoDTO.converte(estoques);

        // Passa os estoques ordenados para anuncio
        anuncioInternoDTO.setEstoques(estoquesDTO);

        return anuncioInternoDTO;
    }

    /**
     * Método que ordena os estoques, utilizado no método getAnuncioByIdOrdered
     * @param (Set<Estoque>) estoques
     * @param (String) orderBy
     * @return (List<Estoque>) estoqueList, uma lista de estoque
     */
    private List<Estoque> getEstoquesOrdenados(Set<Estoque> estoques, String orderBy) {
        List<Estoque> estoqueList = new ArrayList<Estoque>(estoques);
        switch (orderBy) {
            case "L": // ordenados por lote
                estoqueList.sort(Comparator.comparing(Estoque::getId));
//                List<Estoque> estoqueList2 = estoques.stream().sorted((e1, e2) ->
//                        e1.getId().compareTo(e2.getId())).collect(Collectors.toList());
                break;
            case "C": // ordenados por quantidade atual
                estoqueList.sort(Comparator.comparing(Estoque::getQuantidadeAtual));
                break;
            case "F": // ordenados por data de vencimento
                estoqueList.sort(Comparator.comparing(Estoque::getDataValidade));
                break;
        }
        return estoqueList;
    }
}
