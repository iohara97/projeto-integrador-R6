package com.example.demo.dto;

import com.example.demo.entity.OrdemEntrada;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class InboundOrderDTO {

    @Valid
    private OrdemEntradaDTO ordemEntradaDTO;

    @Valid
    private Set<EstoqueDTO> listaEstoqueDTO;

    public static InboundOrderDTO converte(OrdemEntrada ordemEntrada){
        OrdemEntradaDTO ordemEntradaDTO = new OrdemEntradaDTO(ordemEntrada.getDataCriacao(), ordemEntrada.getId());
        Set<EstoqueDTO> listaEstoque = EstoqueDTO.converte(ordemEntrada.getEstoques());

        InboundOrderDTO dto = InboundOrderDTO.builder()
                .ordemEntradaDTO(ordemEntradaDTO)
                .listaEstoqueDTO(listaEstoque)
                .build();
        return dto;
    }
}
