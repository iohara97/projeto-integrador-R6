package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class OrdemEntradaDTO {

    @NotNull(message = "Data de criação em branco, favor fornecê-lo!")
    @PastOrPresent(message = "Data de criação deve ser inferior ou igual a data atual.")
    private LocalDateTime dataCriacao;
    @NotNull(message = "Id do setor em branco, favor fornecê-lo!")
    private Long setor_id;
}
