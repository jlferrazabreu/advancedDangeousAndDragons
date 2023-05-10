package avanade.api.infra.service.validacao.personagem;

import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;
import avanade.api.infra.exception.ValidacaoException;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoAtualizarPersonagem;

public class ValidarAtualizacaoNomeObrigatorio implements ValidacaoAtualizarPersonagem {
    @Override
    public void validar(DadosAtualizacaoPersonagem dados) {
        if (dados.nome() == "") {
            throw new ValidacaoException("O nome do personagem não pode ser vázio");
        }
    }
}
