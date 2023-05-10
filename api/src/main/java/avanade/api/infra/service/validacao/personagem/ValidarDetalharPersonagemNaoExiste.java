package avanade.api.infra.service.validacao.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.infra.exception.ValidacaoException;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoDetalharPersonagem;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarDetalharPersonagemNaoExiste implements ValidacaoDetalharPersonagem {
    @Autowired
    private PersonagemRepository repository;
    @Override
    public void validar(Long id) {
        if(!repository.existsById(id) || id == null){
            throw new ValidacaoException("O id do Personagem não existe!");
        }
    }
}
