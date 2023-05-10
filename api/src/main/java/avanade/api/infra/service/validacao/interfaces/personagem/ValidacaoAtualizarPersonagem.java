package avanade.api.infra.service.validacao.interfaces.personagem;

import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;

public interface ValidacaoAtualizarPersonagem {
    void validar(DadosAtualizacaoPersonagem dados);
}
