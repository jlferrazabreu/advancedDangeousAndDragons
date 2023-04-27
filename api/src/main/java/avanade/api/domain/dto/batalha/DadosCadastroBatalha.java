package avanade.api.domain.dto.batalha;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroBatalha(
        @NotNull
        Long usuario_id
) {
}
