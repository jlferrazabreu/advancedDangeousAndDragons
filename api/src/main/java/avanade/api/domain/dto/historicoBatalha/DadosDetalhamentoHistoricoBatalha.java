package avanade.api.domain.dto.historicoBatalha;

public record DadosDetalhamentoHistoricoBatalha(Long batalhaId, String acao, String proximaAcaoUsuario,
                                                String jogadorUsuario, String personagemUsuario, int numSorteadousuario,
                                                String proximaAcaoComputador, String jogadorComputador,
                                                String personagemComputador, int numSorteadoComputador

) {

    public DadosDetalhamentoHistoricoBatalha(Long batalhaId, String acao, String proximaAcaoUsuario, String jogadorUsuario, String personagemUsuario, int numSorteadousuario, String proximaAcaoComputador, String jogadorComputador, String personagemComputador, int numSorteadoComputador) {
        this.batalhaId = batalhaId;
        this.acao = acao;
        this.proximaAcaoUsuario = proximaAcaoUsuario;
        this.jogadorUsuario = jogadorUsuario;
        this.personagemUsuario = personagemUsuario;
        this.numSorteadousuario = numSorteadousuario;
        this.proximaAcaoComputador = proximaAcaoComputador;
        this.jogadorComputador = jogadorComputador;
        this.personagemComputador = personagemComputador;
        this.numSorteadoComputador = numSorteadoComputador;
    }
}
