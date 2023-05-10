package avanade.api.infra.service.validacao.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.personagem.dto.DadosCadastroPersonagem;
import avanade.api.infra.exception.ValidacaoException;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoCadastrarPersonagem;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarCadastroPersonagemJaCadastrado implements ValidacaoCadastrarPersonagem {
    @Autowired
    PersonagemRepository repository;
    public void validar(DadosCadastroPersonagem dados) {
        if(repository.existsByNome(dados.nome())){
            throw new ValidacaoException("Personagem já cadastrado!");
        }
    }
}
