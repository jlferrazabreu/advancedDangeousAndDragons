package avanade.api.infra.service.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.personagem.dto.DadosDetalhamentoPersonagem;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoDetalharPersonagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalharPersonagemService {
    public DetalharPersonagemService(List<ValidacaoDetalharPersonagem> validacoes) {
        this.validacoes = validacoes;
    }

    @Autowired
    PersonagemRepository repository;
    private List<ValidacaoDetalharPersonagem> validacoes;
    public DadosDetalhamentoPersonagem detalhar(Long id) {
        validacoes.forEach(v -> v.validar(id));
        var personagem = repository.getReferenceById(id);
        return new DadosDetalhamentoPersonagem(personagem);
    }
}
