package avanade.api.infra.service.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;
import avanade.api.domain.personagem.dto.DadosDetalhamentoPersonagem;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoAtualizarPersonagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtualizarPersonagemService {
    public AtualizarPersonagemService(List<ValidacaoAtualizarPersonagem> validacoes) {
        this.validacoes = validacoes;
    }

    @Autowired
    private PersonagemRepository repository;
    private List<ValidacaoAtualizarPersonagem> validacoes;
    public DadosDetalhamentoPersonagem atualizar(DadosAtualizacaoPersonagem dados) {
        validacoes.forEach(v -> v.validar(dados));
        var personagem = repository.getReferenceById(dados.id());
        personagem.atualizarInformacoes(dados);
        return new DadosDetalhamentoPersonagem(personagem);
    }
}
