package avanade.api.infra.service.validacao.personagem;

import avanade.api.domain.personagem.dto.DadosCadastroPersonagem;
import avanade.api.infra.exception.ValidacaoException;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoCadastrarPersonagem;

public class ValidarCadastroNomeObrigatorio implements ValidacaoCadastrarPersonagem {
    public void validar(DadosCadastroPersonagem dados){
        if (dados.nome().isEmpty()) {
            throw new ValidacaoException("O nome do personagem é obrigatorio!");
        }
    }
}
