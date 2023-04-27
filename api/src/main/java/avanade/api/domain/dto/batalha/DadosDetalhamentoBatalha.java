package avanade.api.domain.dto.batalha;

import avanade.api.domain.batalha.Batalha;

import java.time.LocalDateTime;

public record DadosDetalhamentoBatalha(Long usuario_id, Long personagem_id, Boolean ativo, LocalDateTime dataInicio) {
    public DadosDetalhamentoBatalha(Batalha batalha) {
        this(batalha.getUsuario_id(), batalha.getPersonagem_id(), batalha.getAtivo(),batalha.getDataInicio());
    }
}
