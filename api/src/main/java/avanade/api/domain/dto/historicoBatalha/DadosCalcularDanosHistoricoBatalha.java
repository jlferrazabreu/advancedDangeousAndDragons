package avanade.api.domain.dto.historicoBatalha;

import java.time.LocalDateTime;

public record DadosCalcularDanosHistoricoBatalha(Long batalha_id, String jogador, String personagem, String acao ,String proximaAcao, int numSorteado, int dano, int pontosAtaque,int pontosDefesa ,int pontosVida, LocalDateTime data) {

    public DadosCalcularDanosHistoricoBatalha(Long batalha_id, String jogador, String personagem, String acao ,String proximaAcao, int numSorteado, int dano, int pontosAtaque,int pontosDefesa ,int pontosVida, LocalDateTime data) {
        this.batalha_id = batalha_id;
        this.jogador = jogador;
        this.personagem = personagem;
        this.acao = acao;
        this.proximaAcao = proximaAcao;
        this.numSorteado = numSorteado;
        this.dano = dano;
        this.pontosAtaque = pontosAtaque;
        this.pontosDefesa = pontosDefesa;
        this.pontosVida = pontosVida;
        this.data = data;
    }
}
