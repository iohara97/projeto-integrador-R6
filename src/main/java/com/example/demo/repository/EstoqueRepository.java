package com.example.demo.repository;

import com.example.demo.entity.Estoque;
import com.example.demo.entity.OrdemEntrada;
import com.example.demo.interfaces.ListaArmazemInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Long> {
    @Query("SELECT e FROM Estoque e where e.ordemEntrada = ?1")
    List<Estoque> findAllEstoque(OrdemEntrada ordemEntrada);

    @Query(value = "select * from estoque where anuncio_id = ?1", nativeQuery = true)
    List<Estoque> findAllAnuncio(Long anuncio_id);

    @Query(value = " SELECT e.id, e.data_producao, e.data_validade, e.quantidade_atual, e.quantidade_inicial, e.temperatura_atual, e.anuncio_id, e.ordem_entrada_id " +
            " FROM estoque e " +
            " INNER JOIN ordem_entrada oe ON e.ordem_entrada_id = oe.id " +
            " INNER JOIN setor s ON oe.setor_id = s.id " +
            " INNER JOIN representante r ON r.armazem_id = s.armazem_id " +
            " WHERE r.id = :representanteId and s.id = :setorId AND e.data_validade >= :dataValidade", nativeQuery = true)
    List<Estoque> getAnunciosBySetorValidadeRep(Long representanteId, Long setorId, LocalDate dataValidade);

    @Query(value = "SELECT e.id, e.data_producao, e.data_validade, e.quantidade_atual, e.quantidade_inicial, e.temperatura_atual, e.anuncio_id, e.ordem_entrada_id, a.tipo\n" +
            "FROM estoque e " +
            "INNER JOIN anuncio a ON e.anuncio_id = a.id " +
            "WHERE e.data_validade >= :dataValidade AND a.tipo =:tipoProduto " , nativeQuery = true)
    List<Estoque> getEstoqueByDataValidadeCategoria(LocalDate dataValidade, int tipoProduto);

    @Query(value = " SELECT s.armazem_id, SUM(e.quantidade_atual) AS totalQuantidade " +
            "FROM produto AS p " +
            "INNER JOIN anuncio AS a ON p.id = a.produto_id " +
            "INNER JOIN estoque AS e ON a.id = e.anuncio_id " +
            "INNER JOIN ordem_entrada AS oe ON oe.id = e.ordem_entrada_id " +
            "INNER JOIN setor AS s ON oe.setor_id = s.id " +
            "WHERE p.id = :produto_id " +
            "GROUP BY s.armazem_id " , nativeQuery = true)
    List<ListaArmazemInterface> getEstoqueByArmazem(Long produto_id);

}
