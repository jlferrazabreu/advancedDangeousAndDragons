package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.Defesa;

public record DadosDefesaBatalha(Long turno_id, Long personagem_id, int pontos, int defesa, int agilidade) {
    public DadosDefesaBatalha(Defesa defesa) {
        this(defesa.getTurno_id(), defesa.getPersonagem_id(), defesa.getPontos(), defesa.getDefesa(), defesa.getAgilidade());
    }
}
