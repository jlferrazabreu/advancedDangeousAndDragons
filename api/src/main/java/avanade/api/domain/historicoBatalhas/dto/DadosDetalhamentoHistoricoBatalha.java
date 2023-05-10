package avanade.api.domain.historicoBatalhas.dto;

public record DadosDetalhamentoHistoricoBatalha(Long batalhaId, String acao, String proximaAcao,
                                                String jogador, String personagem, int numSorteado,
                                                String proximaAcaoComputador, String jogadorComputador,
                                                String personagemComputador, int numSorteadoComputador

) {

    public DadosDetalhamentoHistoricoBatalha(Long batalhaId, String acao, String proximaAcao, String jogador, String personagem, int numSorteado, String proximaAcaoComputador, String jogadorComputador, String personagemComputador, int numSorteadoComputador) {
        this.batalhaId = batalhaId;
        this.acao = acao;
        this.proximaAcao = proximaAcao;
        this.jogador = jogador;
        this.personagem = personagem;
        this.numSorteado = numSorteado;
        this.proximaAcaoComputador = proximaAcaoComputador;
        this.jogadorComputador = jogadorComputador;
        this.personagemComputador = personagemComputador;
        this.numSorteadoComputador = numSorteadoComputador;
    }
}
