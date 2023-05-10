package avanade.api.infra.service;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.usuario.Usuario;
import avanade.api.domain.usuario.UsuarioRepository;
import avanade.api.domain.usuario.dto.DadosCadastroUsuario;
import avanade.api.domain.usuario.dto.DadosDetalhamentoEscolhePersonagem;
import avanade.api.domain.usuario.dto.DadosDetalhamentoUsuario;
import avanade.api.domain.usuario.dto.DadosEscolherPersonagem;
import avanade.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorDeUsuario {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        if(repository.findByLogin(dados.login()) != null){
            throw new ValidacaoException("Usuário já cadastrado!");
        }
        var usuario = new Usuario(dados.login(),passwordEncoder.encode(dados.senha()));
        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }
    public DadosDetalhamentoEscolhePersonagem escolherPersonagem(DadosEscolherPersonagem dados, Long id) {
        var usuario = repository.getReferenceById(id);
        if(!personagemRepository.existsById(dados.personagem_id())){
            throw  new ValidacaoException("Personagem escolhido não existe!");
        }
        var personagem = personagemRepository.getReferenceById(dados.personagem_id()).getNome();
        usuario.atualizarPersonagemDoUsuario(dados);
        return new DadosDetalhamentoEscolhePersonagem(usuario.getLogin(), personagem);
    }
}
