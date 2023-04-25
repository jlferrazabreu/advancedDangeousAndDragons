package avanade.api.dto.personagem;

import avanade.api.model.personagem.TipoPersonagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPersonagem(
        @NotNull
        TipoPersonagem tipoPersonagem,
        @NotBlank
        String nome,
        @NotBlank
        int vida,
        @NotBlank
        int forca,
        @NotBlank
        int defesa,
        @NotBlank
        int agilidade,
        @NotBlank
        int quantidadeDeDados,
        @NotBlank
        int facesDoDado) {
}
