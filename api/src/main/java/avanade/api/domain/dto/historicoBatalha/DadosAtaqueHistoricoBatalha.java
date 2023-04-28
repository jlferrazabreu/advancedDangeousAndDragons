package avanade.api.domain.dto.historicoBatalha;

public record DadosAtaqueHistoricoBatalha(Long batalhaId, String jogador, String personagem, String acao, String proximaAcao, int numSorteado,int dano,int pontosAtaque, int pontoVida) {
    public DadosAtaqueHistoricoBatalha(Long batalhaId, String jogador, String personagem, String acao, String proximaAcao, int numSorteado,int dano ,int pontosAtaque, int pontoVida) {
        this.batalhaId = batalhaId;
        this.jogador = jogador;
        this.personagem = personagem;
        this.acao = acao;
        this.proximaAcao = proximaAcao;
        this.numSorteado = numSorteado;
        this.dano = dano;
        this.pontosAtaque = pontosAtaque;
        this.pontoVida = pontoVida;

    }
}
