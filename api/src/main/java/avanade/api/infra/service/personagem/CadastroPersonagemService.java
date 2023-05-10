package avanade.api.infra.service.personagem;

import avanade.api.domain.personagem.Personagem;
import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.personagem.dto.DadosCadastroPersonagem;
import avanade.api.domain.personagem.dto.DadosDetalhamentoPersonagem;
import avanade.api.infra.service.validacao.interfaces.personagem.ValidacaoCadastrarPersonagem;
import avanade.api.infra.service.validacao.personagem.ValidarCadastroPersonagemJaCadastrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CadastroPersonagemService {
    private List<ValidacaoCadastrarPersonagem> validacoes;
    public CadastroPersonagemService(List<ValidacaoCadastrarPersonagem> validacoes) {
        this.validacoes = validacoes;
    }
    @Autowired
    PersonagemRepository repository;
    private ValidarCadastroPersonagemJaCadastrado validarCadastroPersonagemJaCadastrado;
    public DadosDetalhamentoPersonagem cadastrar(DadosCadastroPersonagem dados) {
        validacoes.forEach(v -> v.validar(dados));
        var personagem = new Personagem(dados);
        repository.save(personagem);
        return  new DadosDetalhamentoPersonagem(personagem);
    }
}
