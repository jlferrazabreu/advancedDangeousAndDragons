package avanade.api.infra.service;

import avanade.api.domain.batalha.Batalha;
import avanade.api.domain.batalha.BatalhaRepository;
import avanade.api.domain.dto.batalha.DadosCadastroBatalha;
import avanade.api.domain.dto.batalha.DadosDetalhamentoBatalha;
import avanade.api.domain.personagem.Personagem;
import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.usuario.UsuarioRepository;
import avanade.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorDeBatalha {
    @Autowired
    private BatalhaRepository batalhaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PersonagemRepository personagemRepository;

    public DadosDetalhamentoBatalha gerarBatalha(DadosCadastroBatalha dados) {
        var usuario = usuarioRepository.findById(dados.usuario_id()).get();
        if (usuario.getPersonagem_id() == null) {
            throw  new ValidacaoException("Usuario precisa escolher personagem antes de procurar uma batalha!");
        }
        var emBatalha = batalhaRepository.usuarioEmBatalha(usuario.getId());
        if (emBatalha != null) {
            throw  new ValidacaoException("Usuario com batalha em andamento!");
        }
        var personagem = personagemRepository.buscarPersonagem();
        var batalha = new Batalha(new DadosCadastroBatalha(dados.usuario_id()),personagem.getId());
        batalhaRepository.save(batalha);
        return new DadosDetalhamentoBatalha(batalha);
    }

    /*public void iniciarBatalha(Long id) {
        var batalha = repository.getReferenceById(id);
        //var dadoinicial = jogarDado();
        var usuario = personagemRepository.getReferenceById(usuarioRepository.getReferenceById(batalha.getUsuario_id()).getPersonagem_id());
        var computador = personagemRepository.getReferenceById(batalha.getPersonagem_id());
        primeiroAJogar(usuario, computador);
    }*/
    private void jogarDado(int quantidadeDados,int faceDoDado) {
    }

    private void primeiroAJogar(Personagem usuario, Personagem computador) {

    }


}
