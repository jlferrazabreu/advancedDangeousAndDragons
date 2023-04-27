package avanade.api.infra.service;

import avanade.api.domain.dto.personagem.DadosAtualizacaoPersonagem;
import avanade.api.domain.dto.personagem.DadosCadastroPersonagem;
import avanade.api.domain.dto.personagem.DadosDetalhamentoPersonagem;
import avanade.api.domain.dto.personagem.DadosListagemPersonagem;
import avanade.api.domain.personagem.Personagem;
import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorDePersonagem {
    @Autowired
    PersonagemRepository repository;
    public DadosDetalhamentoPersonagem cadastrar(DadosCadastroPersonagem dados) {
        if (dados.nome().isEmpty()) {
            throw new ValidacaoException("O nome do personagem é obrigatorio!");
        }
        if(repository.existsByNome(dados.nome())){
            throw new ValidacaoException("Personagem já cadastrado!");
        }
        var personagem = new Personagem(dados);
        repository.save(personagem);
        return  new DadosDetalhamentoPersonagem(personagem);
    }

    public Page<DadosListagemPersonagem> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPersonagem::new);
        return page;
    }

    public DadosDetalhamentoPersonagem atualizar(DadosAtualizacaoPersonagem dados) {
        if (dados.nome() == "") {
            throw new ValidacaoException("O nome do personagem não pode ser vázio");
        }
        if (dados.id() == null) {
            throw new ValidacaoException("O id do personagem é obrigatorio!");
        }
        if(!repository.existsById(dados.id())){
            throw new ValidacaoException("O id do Personagem não existe!");
        }
        var personagem = repository.getReferenceById(dados.id());
        personagem.atualizarInformacoes(dados);
        return new DadosDetalhamentoPersonagem(personagem);
    }

    public void excluir(Long id) {
        if(!repository.existsById(id) || id == null){
            throw new ValidacaoException("O id do Personagem não existe!");
        }
        var personagem = repository.getReferenceById(id);
        personagem.excluir();
    }

    public DadosDetalhamentoPersonagem detalhar(Long id) {
        if(!repository.existsById(id)){
            throw new ValidacaoException("O id do Personagem não existe!");
        }
        var personagem = repository.getReferenceById(id);
        return new DadosDetalhamentoPersonagem(personagem);
    }
}
