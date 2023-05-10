package avanade.api.infra.service.validacao.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;
import avanade.api.infra.exception.ValidacaoException;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoAtualizarPersonagem;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarAtualizaçãoPersonagemNaoExiste implements ValidacaoAtualizarPersonagem {
    @Autowired
    private PersonagemRepository repository;
    @Override
    public void validar(DadosAtualizacaoPersonagem dados) {
        if(!repository.existsById(dados.id())){
            throw new ValidacaoException("O id do Personagem não existe!");
        }
    }
}
