package avanade.api.domain.personagem.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPersonagem(
        @NotNull
        Long id,
        String nome,
        int vida,
        int forca,
        int defesa,
        int agilidade) {
}
