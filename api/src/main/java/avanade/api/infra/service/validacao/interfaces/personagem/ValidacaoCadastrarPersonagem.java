package avanade.api.infra.service.validacao.interfaces.personagem;

import avanade.api.domain.personagem.dto.DadosCadastroPersonagem;

public interface ValidacaoCadastrarPersonagem {
    void validar(DadosCadastroPersonagem dados);
}
