package avanade.api.infra.service;

import avanade.api.domain.batalha.Batalha;
import avanade.api.domain.batalha.BatalhaRepository;
import avanade.api.domain.dto.batalha.DadosCadastroBatalha;
import avanade.api.domain.dto.batalha.DadosDetalhamentoBatalha;
import avanade.api.domain.dto.batalha.DadosEncerramentoBatalha;
import avanade.api.domain.dto.historicoBatalha.DadosAtaqueHistoricoBatalha;
import avanade.api.domain.dto.historicoBatalha.DadosCadastroHistoricoBatalha;
import avanade.api.domain.dto.historicoBatalha.DadosDefesaHistoricoBatalha;
import avanade.api.domain.dto.historicoBatalha.DadosDetalhamentoHistoricoBatalha;
import avanade.api.domain.historicoBatalhas.HistoricoBatalha;
import avanade.api.domain.historicoBatalhas.HistoricoBatalhaRepository;
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
    Random random = new Random();

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
        var historicobatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), "USUARIO",personagemUsuario.getNome(),"GERAR",proximaAcao,0,0,0,0,personagemUsuario.getVida(), LocalDateTime.now()));
        historicoBatalhaRepository.save(historicobatalha);
        return new DadosDetalhamentoBatalha(batalha);
    }

    public DadosDetalhamentoHistoricoBatalha iniciarBatalha(Long id) {
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
        var usuario = personagemRepository.findById(batalha.getPersonagem_usuario_id()).get();
        var computador = personagemRepository.findById(batalha.getPersonagem_id()).get();

        while (numSorteadoUsuario == numSorteadoComputador){
            numSorteadoUsuario = random.nextInt(faceDoDado)+1;
            numSorteadoComputador = random.nextInt(faceDoDado)+1;
        }
        if (numSorteadoUsuario < numSorteadoComputador) {
            proximaAcaoUsuario = "DEFENDER";
            proximaAcaoComputador = "ATACAR";
        }
        var hBUsuario = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), "USUARIO",usuario.getNome(),"INICIAR", proximaAcaoUsuario,numSorteadoUsuario,0,0 ,0 ,usuario.getVida(), LocalDateTime.now()));
        var hBComputador = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), "COMPUTADOR",computador.getNome(),"INICIAR", proximaAcaoComputador,numSorteadoComputador,0,0 ,0 ,computador.getVida(), LocalDateTime.now()));
        historicoBatalhaRepository.save(hBUsuario);
        historicoBatalhaRepository.save(hBComputador);
        return new DadosDetalhamentoHistoricoBatalha(hBUsuario.getBatalha_id(), hBUsuario.getAcao(), hBUsuario.getProximaAcao(),
                hBUsuario.getJogador(),hBUsuario.getPersonagem(),hBUsuario.getNumSorteado(),hBComputador.getProximaAcao(),
                hBComputador.getJogador(),hBComputador.getPersonagem(),hBComputador.getNumSorteado());
    }

    public DadosAtaqueHistoricoBatalha ataque(Long id) {
        var proximaAcao = "DANO";
        var faceDoDado = 12;
        var personagem = personagemRepository.findById(batalhaRepository.findById(id).get().getId()).get();
        var jogador = historicoBatalhaRepository.buscarAtacante(id);
        var batalha = batalhaRepository.findById(id).get();
        var numSorteado = random.nextInt(faceDoDado)+1;
        var pontos = numSorteado + personagem.getForca() + personagem.getAgilidade();
        var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), jogador.getJogador(),jogador.getPersonagem(), jogador.getProximaAcao(), proximaAcao,numSorteado,0,pontos ,0 ,jogador.getPontosVida(), LocalDateTime.now()));
        historicoBatalhaRepository.save(historicoBatalha);
        return new DadosAtaqueHistoricoBatalha(jogador.getBatalha_id(), jogador.getJogador(), jogador.getPersonagem(), jogador.getProximaAcao(), proximaAcao, jogador.getNumSorteado(), jogador.getDano(),pontos, jogador.getPontosVida());
    }

    public DadosDefesaHistoricoBatalha defesa(Long id) {
        var proximaAcao = "DANO";
        var faceDoDado = 12;
        var personagem = personagemRepository.findById(batalhaRepository.findById(id).get().getId()).get();
        var jogador = historicoBatalhaRepository.buscarDefensor(id);
        var batalha = batalhaRepository.findById(id).get();
        var numSorteado = random.nextInt(faceDoDado)+1;
        var pontos = numSorteado + personagem.getForca() + personagem.getAgilidade();
        var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), jogador.getJogador(),jogador.getPersonagem(), jogador.getProximaAcao(), proximaAcao,numSorteado,0,0 ,pontos ,jogador.getPontosVida(), LocalDateTime.now()));
        historicoBatalhaRepository.save(historicoBatalha);
        return new DadosDefesaHistoricoBatalha(jogador.getBatalha_id(), jogador.getJogador(), jogador.getPersonagem(), jogador.getProximaAcao(), proximaAcao, jogador.getNumSorteado(), jogador.getDano(),pontos, jogador.getPontosVida());
    }

    public DadosCadastroHistoricoBatalha calculardano(Long id) {
        HistoricoBatalha historicoBatalha;
        var jogadorAtaque = historicoBatalhaRepository.buscarAtacanteDano(id);
        var jogadorDefesa = historicoBatalhaRepository.buscarDefensorDano(id);
        var batalha = batalhaRepository.findById(id).get();
        var personagem = personagemRepository.findById(batalhaRepository.findById(id).get().getId()).get();
        var proximaAcao = "PONTOS_VIDA";
        var quantidadeDados = personagem.getQuantidadeDeDados();
        var faceDoDado = personagem.getFacesDoDado();
        var numSorteado = jogarDado(quantidadeDados, faceDoDado);
        var pontos = numSorteado + personagem.getForca();
        if (jogadorAtaque.getPontosAtaque() <= jogadorDefesa.getPontosDefesa()) {
            var dadosHistoricoBatalha = new DadosCadastroHistoricoBatalha(batalha.getId(), jogadorDefesa.getJogador(),jogadorDefesa.getPersonagem(), jogadorDefesa.getProximaAcao(), "INICIAR", numSorteado,0,0 ,jogadorDefesa.getPontosDefesa(),jogadorDefesa.getPontosVida(), LocalDateTime.now());
            historicoBatalha = new HistoricoBatalha(dadosHistoricoBatalha);
            historicoBatalhaRepository.save(historicoBatalha);
            return new DadosCadastroHistoricoBatalha(batalha.getId(), jogadorDefesa.getJogador(),jogadorDefesa.getPersonagem(), jogadorDefesa.getProximaAcao(), "INICIAR", numSorteado,0,0 ,jogadorDefesa.getPontosDefesa(),jogadorDefesa.getPontosVida(),LocalDateTime.now());
        }else {
            historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), jogadorAtaque.getJogador(), jogadorAtaque.getPersonagem(), jogadorAtaque.getProximaAcao(), proximaAcao, numSorteado, pontos, 0, jogadorDefesa.getPontosDefesa(), jogadorAtaque.getPontosVida(), LocalDateTime.now()));
            historicoBatalhaRepository.save(historicoBatalha);
            return new DadosCadastroHistoricoBatalha(batalha.getId(), jogadorAtaque.getJogador(), jogadorAtaque.getPersonagem(), jogadorAtaque.getProximaAcao(), proximaAcao, numSorteado, pontos, 0, jogadorDefesa.getPontosDefesa(), jogadorAtaque.getPontosVida(), LocalDateTime.now());
        }
    }

    public DadosCadastroHistoricoBatalha calcularpontosvida(Long id) {
        HistoricoBatalha historicoBatalha;
        var jogadorAtaque = historicoBatalhaRepository.buscarAtacantePontosVida(id);
        var jogadorDefesa = historicoBatalhaRepository.buscarDefensorPontosVida(id);
        var batalha = batalhaRepository.findById(id).get();
        var proximaAcao = "INICIAR";
        var pontosVida = jogadorDefesa.getPontosVida() - jogadorAtaque.getDano();
        if (pontosVida <= 0) {
            var dadosHistoricoBatalha = new DadosCadastroHistoricoBatalha(id, jogadorDefesa.getJogador(),jogadorDefesa.getPersonagem(), jogadorDefesa.getProximaAcao(), proximaAcao, jogadorDefesa.getNumSorteado(),0,0 ,jogadorDefesa.getPontosDefesa(),jogadorDefesa.getPontosVida(), LocalDateTime.now());
            historicoBatalha = new HistoricoBatalha(dadosHistoricoBatalha);
            historicoBatalhaRepository.save(historicoBatalha);
            batalha.atualizarBatalha(new DadosEncerramentoBatalha(false, LocalDateTime.now()));

            return new DadosCadastroHistoricoBatalha(id, jogadorDefesa.getJogador(),jogadorDefesa.getPersonagem(), jogadorDefesa.getProximaAcao(), proximaAcao, jogadorDefesa.getNumSorteado(),0,0 ,jogadorDefesa.getPontosDefesa(),jogadorDefesa.getPontosVida(), LocalDateTime.now());
        }


        return new DadosCadastroHistoricoBatalha(id, jogadorAtaque.getJogador(), jogadorAtaque.getPersonagem(), jogadorAtaque.getProximaAcao(), proximaAcao, 0, 0, 0, jogadorDefesa.getPontosDefesa(), jogadorAtaque.getPontosVida(), LocalDateTime.now());
    }
    private int jogarDado(int quantidadeDados,int faceDoDado) {
            var novoNumero = 0;
            for (int i = 0; i < quantidadeDados; i++) {
                Random random = new Random();
                var numero = random.nextInt(faceDoDado)+1;
                novoNumero += numero;
            }
            return novoNumero;
    }
}
