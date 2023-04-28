package avanade.api.domain.dto.historicoBatalha;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosCadastroHistoricoBatalha(
        @NotBlank
        Long batalha_id,
        @NotBlank
        String jogador,
        @NotBlank
        String personagem,
        @NotBlank
        String acao,
        String proximaAcao,
        int numSorteado,
        int dano,
        int pontosAtaque,
        int pontosDefesa,
        int pontosVida,
        LocalDateTime data
) {
}
