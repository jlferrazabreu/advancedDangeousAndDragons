package avanade.api.infra.service;

import avanade.api.domain.batalha.Batalha;
import avanade.api.domain.batalha.BatalhaRepository;
import avanade.api.domain.batalha.HistoricoBatalha;
import avanade.api.domain.batalha.HistoricoBatalhaRepository;
import avanade.api.domain.dto.batalha.DadosCadastroBatalha;
import avanade.api.domain.dto.batalha.DadosDetalhamentoBatalha;
import avanade.api.domain.dto.historicoBatalha.DadosCadastroHistoricoBatalha;
import avanade.api.domain.dto.historicoBatalha.DadosDetalhamentoHistoricoBatalha;
import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.usuario.UsuarioRepository;
import avanade.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class GerenciadorDeBatalha {
    @Autowired
    private BatalhaRepository batalhaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private HistoricoBatalhaRepository historicoBatalhaRepository;

    public DadosDetalhamentoBatalha gerarBatalha(DadosCadastroBatalha dados) {
        var usuario = usuarioRepository.findById(dados.usuario_id()).get();
        if (usuario.getPersonagem_id() == null) {
            throw  new ValidacaoException("Usuario precisa escolher personagem antes de procurar uma batalha!");
        }
        var emBatalha = batalhaRepository.usuarioEmBatalha(usuario.getId());
        if (emBatalha != null) {
            throw  new ValidacaoException("Usuario com batalha em andamento!");
        }
        var proximaAcao = "INICIAR";
        var personagemUsuario = personagemRepository.findById(usuario.getPersonagem_id()).get();
        var personagem = personagemRepository.buscarPersonagem(personagemUsuario.getId());
        var batalha = new Batalha(new DadosCadastroBatalha(dados.usuario_id()),personagem.getId(),personagemUsuario.getId());
        batalhaRepository.save(batalha);
        var historicobatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), "USUARIO",personagemUsuario.getNome(),"GERAR",proximaAcao,0,0,personagemUsuario.getVida(), LocalDateTime.now()));
        historicoBatalhaRepository.save(historicobatalha);
        return new DadosDetalhamentoBatalha(batalha);
    }

    public DadosDetalhamentoHistoricoBatalha iniciarBatalha(Long id) {
        Random random = new Random();
        var numSorteadoUsuario = 0;
        var numSorteadoComputador = 0;
        var proximaAcaoUsuario = "ATACAR";
        var proximaAcaoComputador = "DEFENDER";
        var hbbatalha = historicoBatalhaRepository.buscarIniciado(id);
        if (hbbatalha != null) {
            throw  new ValidacaoException("Batalha já foi iniciada!");
        }
        var faceDoDado = 20;
        var batalha = batalhaRepository.findById(id).get();
        var usuario = personagemRepository.getReferenceById(batalha.getPersonagem_usuario_id());
        var computador = personagemRepository.getReferenceById(batalha.getPersonagem_id());

        while (numSorteadoUsuario == numSorteadoComputador){
            numSorteadoUsuario = random.nextInt(faceDoDado)+1;
            numSorteadoComputador = random.nextInt(faceDoDado)+1;
        }
        if (numSorteadoUsuario < numSorteadoComputador) {
            proximaAcaoUsuario = "DEFENDER";
            proximaAcaoComputador = "ATACAR";
        }
        var hBUsuario = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), "USUARIO",usuario.getNome(),"INICIAR", proximaAcaoUsuario,numSorteadoUsuario,0,usuario.getVida(), LocalDateTime.now()));
        var hBComputador = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), "COMPUTADOR",computador.getNome(),"INICIAR", proximaAcaoComputador,numSorteadoComputador,0,computador.getVida(), LocalDateTime.now()));
        historicoBatalhaRepository.save(hBUsuario);
        historicoBatalhaRepository.save(hBComputador);
        return new DadosDetalhamentoHistoricoBatalha(hBUsuario.getBatalha_id(), hBUsuario.getAcao(), hBUsuario.getProximaAcao(),
                hBUsuario.getJogador(),hBUsuario.getPersonagem(),hBUsuario.getNumSorteado(),hBComputador.getProximaAcao(),
                hBComputador.getJogador(),hBComputador.getPersonagem(),hBComputador.getNumSorteado());
    }


    private int jogarDado(int quantidadeDados,int faceDoDado) {
            var numero = 0;
            for (int i = 0; i < quantidadeDados; i++) {
                Random random = new Random();
                numero = random.nextInt(faceDoDado);
                numero += numero;
            }
            return numero;
    }


}
