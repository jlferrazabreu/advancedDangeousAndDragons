package avanade.api.domain.dto.historicoBatalha;

public record DadosDefesaHistoricoBatalha(Long batalhaId, String jogador, String personagem, String acao, String proximaAcao, int numSorteado,int dano,int pontosDefesa, int pontoVida) {
    public DadosDefesaHistoricoBatalha(Long batalhaId, String jogador, String personagem, String acao, String proximaAcao, int numSorteado,int dano ,int pontosDefesa, int pontoVida) {
        this.batalhaId = batalhaId;
        this.jogador = jogador;
        this.personagem = personagem;
        this.acao = acao;
        this.proximaAcao = proximaAcao;
        this.numSorteado = numSorteado;
        this.dano = dano;
        this.pontosDefesa = pontosDefesa;
        this.pontoVida = pontoVida;

    }
}
