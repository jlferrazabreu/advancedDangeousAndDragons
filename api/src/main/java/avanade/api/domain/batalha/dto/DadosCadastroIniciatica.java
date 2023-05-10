package avanade.api.domain.batalha.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroIniciatica(
    @NotNull
    Long batalha_id,
    @NotNull
    Long personagem_id,
    String personagem,
    Boolean ativo,
    int pontos
) {
}
