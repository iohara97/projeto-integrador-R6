package com.example.demo.dto;

import com.example.demo.entity.Anuncio;
import com.example.demo.entity.Estoque;
import com.example.demo.entity.OrdemEntrada;
import jdk.jfr.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class EstoqueDTO {

    private Long id;
    @NotNull(message = "Anuncio em branco, favor fornecê-lo!")
    @Min(value = 1, message = "Favor informar o id de um anuncio válido!")
    private Long anuncio_id;
    @Null(message = "Campo apenas de resposta")
    private Long ordem_entrada_id;
    @NotNull(message = "Quantidade inicial em branco, favor fornecê-la!")
    @Min(value = 1, message = "Favor informar uma quantidade inicial maior que zero.")
    private Integer quantidadeInicial;
    @NotNull(message = "Quantidade atual em branco, favor fornecê-la!")
    @Min(value = 1, message = "Favor informar uma quantidade atual maior que zero.")
    private Integer quantidadeAtual;
    @NotNull(message = "Temperatura atual em branco, favor fornecê-la!")
    private Double temperaturaAtual;
    @NotNull(message = "Data de valida em branco, favor fornecê-la!")
    @Future(message = "So permitido data futura para validade.")
    private LocalDate dataValidade;
    @NotNull(message = "Data de produção em branco, favor fornecê-la!")
    @PastOrPresent(message = "Informe uma data de produçao inferior ou igual a data atual.")
    private LocalDateTime dataProducao;

    public static Estoque converte(EstoqueDTO dto, Anuncio anuncio, OrdemEntrada ordemEntrada) {
        return Estoque.builder()
                .id(dto.getId())
                .anuncio(anuncio)
                .ordemEntrada(ordemEntrada)
                .quantidadeInicial(dto.getQuantidadeInicial())
                .quantidadeAtual(dto.getQuantidadeAtual())
                .temperaturaAtual(dto.getTemperaturaAtual())
                .dataValidade(dto.getDataValidade())
                .dataProducao(dto.getDataProducao())
                .build();
    }

    public static EstoqueDTO converte(Estoque e) {
        EstoqueDTO dto = EstoqueDTO.builder()
                .id(e.getId())
                .anuncio_id(e.getAnuncio().getId())
                .ordem_entrada_id(e.getOrdemEntrada().getId())
                .quantidadeInicial(e.getQuantidadeInicial())
                .quantidadeAtual(e.getQuantidadeAtual())
                .temperaturaAtual(e.getTemperaturaAtual())
                .dataValidade(e.getDataValidade())
                .dataProducao(e.getDataProducao())
                .build();
        return dto;
    }

    public static List<EstoqueDTO> converte(List<Estoque> estoque) {
        return estoque.stream().map(EstoqueDTO::converte).collect(Collectors.toList());
    }

    public static Set<EstoqueDTO> converte(Set<Estoque> estoque) {
        return estoque.stream().map(EstoqueDTO::converte).collect(Collectors.toSet());
    }
}
