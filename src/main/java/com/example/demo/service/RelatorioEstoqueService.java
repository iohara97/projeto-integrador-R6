package com.example.demo.service;

import com.example.demo.dto.ProdutosEstoqueBaixoDTO;
import com.example.demo.dto.ProdutosParaVencerDTO;
import com.example.demo.dto.RelatorioEstoqueDTO;
import com.example.demo.entity.Armazem;
import com.example.demo.entity.Estoque;
import com.example.demo.entity.Setor;
import com.example.demo.enums.Tipos;
import com.example.demo.repository.ArmazemRepository;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioEstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    SetorRepository setorRepository;

    @Autowired
    ArmazemRepository armazemRepository;


    public RelatorioEstoqueService(EstoqueRepository estoqueRepository, SetorRepository setorRepository, ArmazemRepository armazemRepository) {
        this.estoqueRepository = estoqueRepository;
        this.setorRepository = setorRepository;
        this.armazemRepository = armazemRepository;
    }

    /** Método para retornar um RelatorioEstoqueDTO
     * com estoqueMinimo definido no parametro da requisição
     * @return (RelatorioEstoqueDTO) RelatorioEstoqueDTO personalizado com o estoque mínimo definido pelo cliente
     */
    public RelatorioEstoqueDTO getRelatorioEstoque(Integer estoqueMinimo) {

        List<Estoque> estoqueList = estoqueRepository.findAll();
        List<ProdutosEstoqueBaixoDTO> produtosEstoqueBaixo = new ArrayList<>();
        List<ProdutosParaVencerDTO> produtosParaVencer = new ArrayList<>();

        estoqueList.forEach(e -> {
            if( e.getQuantidadeAtual() <= estoqueMinimo){
                produtosEstoqueBaixo.add(ProdutosEstoqueBaixoDTO.converte(e));
            }
            if( e.getDataValidade().isBefore(LocalDate.now().plusDays(21))){
                produtosParaVencer.add(ProdutosParaVencerDTO.converte(e));
            }
        });

        if( produtosEstoqueBaixo.size() == 0 && produtosParaVencer.size() == 0) {
            throw new RuntimeException("Não há estoque para exibir. Sem produtos próximos do vencimento ou com estoque baixo.");
        }

        return devolveBuildRelatorio(produtosEstoqueBaixo, produtosParaVencer);
    }

    /** Método para retornar um RelatorioEstoqueDTO
     * com categoria e estoqueMinimo definidos no parametro da requisição
     * @return (RelatorioEstoqueDTO) RelatorioEstoqueDTO personalizado com o estoque mínimo e tipo do anúncio definidos pelo cliente
     */
    public RelatorioEstoqueDTO getRelatorioEstoquePorCategoria(String categoria, Integer estoqueMinimo) {

        List<Estoque> estoqueList = estoqueRepository.findAll();
        List<ProdutosEstoqueBaixoDTO> produtosEstoqueBaixo = new ArrayList<>();
        List<ProdutosParaVencerDTO> produtosParaVencer = new ArrayList<>();

        if (!categoria.equalsIgnoreCase(Tipos.CONGELADO.getDescricao()) && !categoria.equalsIgnoreCase(Tipos.FRESCO.getDescricao()) &&
            !categoria.equalsIgnoreCase(Tipos.REFRIGERADO.getDescricao())) {
            throw new RuntimeException("Não é possível gerar relatório com essa categoria. Verifique se ela está cadastrada.");
        }

        estoqueList.forEach(e -> {
            if( e.getAnuncio().getTipo().getDescricao().equalsIgnoreCase(categoria) && e.getQuantidadeAtual() <= estoqueMinimo){
                produtosEstoqueBaixo.add(ProdutosEstoqueBaixoDTO.converte(e));
            }
            if( e.getAnuncio().getTipo().getDescricao().equalsIgnoreCase(categoria) && e.getDataValidade().isBefore(LocalDate.now().plusDays(21))){
                produtosParaVencer.add(ProdutosParaVencerDTO.converte(e));
            }
        });

        if( produtosEstoqueBaixo.size() == 0 && produtosParaVencer.size() == 0) {
            throw new RuntimeException("Não há estoque para exibir. Sem produtos próximos do vencimento ou com estoque baixo.");
        }

        return devolveBuildRelatorio(produtosEstoqueBaixo, produtosParaVencer);
    }

    /** Método para retornar um RelatorioEstoqueDTO
     * com setorId e estoqueMinimo definidos no parametro da requisição
     * @return (RelatorioEstoqueDTO) RelatorioEstoqueDTO personalizado com o estoque mínimo e id do setor definidos pelo cliente
     */
    public RelatorioEstoqueDTO getRelatorioEstoquePorSetor(Long setorId, Integer estoqueMinimo) {

        List<Estoque> estoqueList = estoqueRepository.findAll();
        List<ProdutosEstoqueBaixoDTO> produtosEstoqueBaixo = new ArrayList<>();
        List<ProdutosParaVencerDTO> produtosParaVencer = new ArrayList<>();

       if( this.setorRepository.findById(setorId).orElse(new Setor()).getId() == null) {
           throw new RuntimeException("Não é possível gerar relatório com esse setor. Verifique se ele existe.");
       }

        estoqueList.forEach(e -> {
            if( e.getOrdemEntrada().getSetor().getId().equals(setorId) && e.getQuantidadeAtual() <= estoqueMinimo){
                produtosEstoqueBaixo.add(ProdutosEstoqueBaixoDTO.converte(e));
            }
            if( e.getOrdemEntrada().getSetor().getId().equals(setorId) && e.getDataValidade().isBefore(LocalDate.now().plusDays(21))){
                produtosParaVencer.add(ProdutosParaVencerDTO.converte(e));
            }
        });

        if( produtosEstoqueBaixo.size() == 0 && produtosParaVencer.size() == 0) {
            throw new RuntimeException("Não há estoque para exibir. Sem produtos próximos do vencimento ou com estoque baixo.");
        }

        return devolveBuildRelatorio(produtosEstoqueBaixo, produtosParaVencer);
    }

    /** Método para retornar um RelatorioEstoqueDTO
     * com armazemId e estoqueMinimo definidos no parametro da requisição
     * @return (RelatorioEstoqueDTO) RelatorioEstoqueDTO personalizado com o estoque mínimo e id do armazem definidos pelo cliente
     */
    public RelatorioEstoqueDTO getRelatorioEstoquePorArmazem(Long armazemId, Integer estoqueMinimo) {

        List<Estoque> estoqueList = estoqueRepository.findAll();
        List<ProdutosEstoqueBaixoDTO> produtosEstoqueBaixo = new ArrayList<>();
        List<ProdutosParaVencerDTO> produtosParaVencer = new ArrayList<>();

        if( this.armazemRepository.findById(armazemId).orElse(new Armazem()).getId() == null) {
            throw new RuntimeException("Não é possível gerar relatório com esse armazem. Verifique se ele existe.");
        }

        estoqueList.forEach(e -> {
            if( e.getOrdemEntrada().getSetor().getArmazem().getId().equals(armazemId) && e.getQuantidadeAtual() <= estoqueMinimo){
                produtosEstoqueBaixo.add(ProdutosEstoqueBaixoDTO.converte(e));
            }
            if( e.getOrdemEntrada().getSetor().getArmazem().getId().equals(armazemId) && e.getDataValidade().isBefore(LocalDate.now().plusDays(21))){
                produtosParaVencer.add(ProdutosParaVencerDTO.converte(e));
            }
        });

        if( produtosEstoqueBaixo.size() == 0 && produtosParaVencer.size() == 0) {
            throw new RuntimeException("Não há estoque para exibir. Sem produtos próximos do vencimento ou com estoque baixo.");
        }

        return devolveBuildRelatorio(produtosEstoqueBaixo, produtosParaVencer);
    }

    /** Método para retornar um build de RelatorioEstoqueDTO
     * @return (RelatorioEstoqueDTO) RelatorioEstoqueDTO personalizado com uma lista de produtosEstoqueBaixo e produtosParaVencer
     */
    private RelatorioEstoqueDTO devolveBuildRelatorio(List<ProdutosEstoqueBaixoDTO> produtosEstoqueBaixo, List<ProdutosParaVencerDTO> produtosParaVencer) {

        return RelatorioEstoqueDTO.builder()
                .produtosEstoqueBaixo(produtosEstoqueBaixo)
                .totalDeProdutosEstoqueBaixo(produtosEstoqueBaixo.size())
                .produtosParaVencer(produtosParaVencer)
                .totalDeProdutosAVencer(produtosParaVencer.size())
                .build();
    }
}
