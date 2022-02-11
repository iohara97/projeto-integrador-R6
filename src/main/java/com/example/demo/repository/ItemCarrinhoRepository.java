package com.example.demo.repository;

import com.example.demo.entity.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
    @Query(value = "select SUM(preco * quantidade) from item_carrinho where carrinho_id = :id", nativeQuery = true)
    Double totalPreco(Long id);
}
