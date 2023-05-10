package avanade.api.infra.service.validacao.personagem;

import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;
import avanade.api.infra.exception.ValidacaoException;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoAtualizarPersonagem;

public class ValidarAtualizacaoIdObrigatorio implements ValidacaoAtualizarPersonagem {
    @Override
    public void validar(DadosAtualizacaoPersonagem dados) {
        if (dados.id() == null) {
            throw new ValidacaoException("O id do personagem é obrigatorio!");
        }
    }
}
