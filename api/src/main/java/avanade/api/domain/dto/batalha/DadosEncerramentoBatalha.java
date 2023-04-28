package avanade.api.domain.dto.batalha;

import avanade.api.domain.batalha.Batalha;

import java.time.LocalDateTime;

public record DadosEncerramentoBatalha(boolean ativo, LocalDateTime dataFinal) {
    public DadosEncerramentoBatalha (Batalha batalha){
        this(batalha.getAtivo(), batalha.getDataFinal());
    }
}
