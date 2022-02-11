package com.example.demo.dto;

import com.example.demo.entity.Anuncio;
import com.example.demo.entity.Carrinho;
import com.example.demo.entity.ItemCarrinho;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ItemCarrinhoDTO {

    private Long id;
    @NotNull(message = "Id do anuncio em branco, favor fornecê-lo!")
    private Long anuncio_id;
    @NotNull(message = "Quantidade em branco, favor fornecê-lo!")
    @Min(value = 0, message = "Favor fornecer as quantidades do pedido")
    private Integer quantidade;

    public static ItemCarrinho converte(ItemCarrinhoDTO dto, Anuncio anuncio, Carrinho carrinho) {
        return ItemCarrinho.builder()
                .id(dto.getId())
                .anuncio(anuncio)
                .carrinho(carrinho)
                .quantidade(dto.getQuantidade())
                .preco(anuncio.getPreco())
                .build();
    }

    public static ItemCarrinhoDTO converte(ItemCarrinho itemCarrinho) {
        return ItemCarrinhoDTO.builder()
                .id(itemCarrinho.getId())
                .anuncio_id(itemCarrinho.getAnuncio().getId())
                .quantidade(itemCarrinho.getQuantidade())
                .build();
    }

    public static Set<ItemCarrinhoDTO> converte(Set<ItemCarrinho> itemCarrinho) {
        return itemCarrinho.stream().map(ItemCarrinhoDTO::converte).collect(Collectors.toSet());
    }
}
