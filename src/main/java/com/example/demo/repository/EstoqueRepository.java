package com.example.demo.repository;

import com.example.demo.dto.ListaArmazemDTO;
import com.example.demo.entity.Anuncio;
import com.example.demo.entity.Estoque;
import com.example.demo.entity.OrdemEntrada;
import com.example.demo.interfaces.ListaArmazemInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Long> {
    @Query("SELECT e FROM Estoque e where e.ordemEntrada = ?1")
    List<Estoque> findAllEstoque(OrdemEntrada ordemEntrada);

    @Query(value = "select * from estoque where anuncio_id = ?1", nativeQuery = true)
    List<Estoque> findAllAnuncio(Long anuncio_id);

    @Query(value = " SELECT s.armazem_id, SUM(e.quantidade_atual) AS totalProduto " +
            "FROM produto AS p " +
            "INNER JOIN anuncio AS a ON p.id = a.produto_id " +
            "INNER JOIN estoque AS e ON a.id = e.anuncio_id " +
            "INNER JOIN ordem_entrada AS oe ON oe.id = e.ordem_entrada_id " +
            "INNER JOIN setor AS s ON oe.setor_id = s.id " +
            "WHERE p.id = :produto_id " +
            "GROUP by s.armazem_id " , nativeQuery = true)
    List<ListaArmazemInterface> getEstoqueByArmazem(Long produto_id);

}
