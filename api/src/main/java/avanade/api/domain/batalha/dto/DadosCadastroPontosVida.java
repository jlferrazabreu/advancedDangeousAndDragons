package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.PontosVida;

public record DadosCadastroPontosVida(Long batalha_id, Long personagem_id, int valorDano, int pontosVida, Boolean ativo) {
    public DadosCadastroPontosVida(PontosVida pontosVida) {
        this(pontosVida.getBatalha_id(), pontosVida.getPersonagem_id(), pontosVida.getValorDano(), pontosVida.getPontosVida(), pontosVida.getAtivo());
    }
}
