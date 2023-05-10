package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.Dano;

public record DadosDanoBatalha(Long turno_id, Long personagem_id, int valorDano, int qtdDado, int faceDado) {
    public DadosDanoBatalha(Dano dano) {
        this(dano.getTurno_id(), dano.getPersonagem_id(), dano.getValorDano(), dano.getQtdDado(), dano.getFaceDado());
    }
}
