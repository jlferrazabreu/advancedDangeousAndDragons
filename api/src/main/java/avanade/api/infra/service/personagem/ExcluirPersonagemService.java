package avanade.api.infra.service.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoExcluirPersonagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcluirPersonagemService {
    public ExcluirPersonagemService(List<ValidacaoExcluirPersonagem> validacoes) {
        this.validacoes = validacoes;
    }

    @Autowired
    private PersonagemRepository repository;
    private List<ValidacaoExcluirPersonagem> validacoes;
    public void excluir(Long id) {
        validacoes.forEach(v -> v.validar(id));
        var personagem = repository.getReferenceById(id);
        personagem.excluir();
    }
}
