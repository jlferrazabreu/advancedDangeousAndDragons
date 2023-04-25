package avanade.api.dto.personagem;

import avanade.api.model.personagem.TipoPersonagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPersonagem(
        @NotNull
        TipoPersonagem tipoPersonagem,
        @NotBlank
        String nome,
        @NotNull
        int vida,
        @NotNull
        int forca,
        @NotNull
        int defesa,
        @NotNull
        int agilidade,
        @NotNull
        int quantidadeDeDados,
        @NotNull
        int facesDoDado) {
}
