package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.Batalha;

import java.time.LocalDateTime;

public record DadosDetalhamentoBatalha(Long usuario_id, Long personUsuario_id, Long personComputador_id, LocalDateTime dataInicio, LocalDateTime dataFinal, Boolean ativo, String vencedor) {
    public DadosDetalhamentoBatalha(Batalha batalha) {
        this(batalha.getUsuario_id(), batalha.getPersonUsuario_id(),batalha.getPersonComputador_id(),batalha.getDataInicio(),batalha.getDataFinal() ,batalha.getAtivo(), batalha.getVencedor());
    }
}
