package avanade.api.domain.batalha.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroBatalha(
        @NotNull
        Long usuario_id,
        Long personUsuario_id,
        Long personComputador_id,
        LocalDateTime dataInicio,
        LocalDateTime dataFinal,
        Boolean ativo,
        String vencedor
) {
}
