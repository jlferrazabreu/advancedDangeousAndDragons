package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.Ataque;

public record DadosAtaqueBatalha(Long turno_id, Long personagem_id, int pontos, int forca, int agilidade) {
    public DadosAtaqueBatalha(Ataque ataque) {
        this(ataque.getTurno_id(), ataque.getPersonagem_id(), ataque.getPontos(), ataque.getForca(), ataque.getAgilidade());
    }
}
