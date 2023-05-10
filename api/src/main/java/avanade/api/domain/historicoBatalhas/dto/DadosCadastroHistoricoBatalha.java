package avanade.api.domain.historicoBatalhas.dto;

import avanade.api.domain.historicoBatalhas.Acao;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroHistoricoBatalha(
        @NotNull
        Long batalha_id,

        Long turno_id,

        String personagem,
        Acao acao,
        Long pontosInicio,
        Long pontosAtaque,
        Long pontosDefesa,
        Long qtdDano,
        Long pontosVida,
        String descricao,
        LocalDateTime data
) {
}
