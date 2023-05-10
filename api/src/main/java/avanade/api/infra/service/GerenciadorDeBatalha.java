package avanade.api.infra.service;

import avanade.api.domain.batalha.*;
import avanade.api.domain.batalha.dto.*;
import avanade.api.domain.historicoBatalhas.Acao;
import avanade.api.domain.historicoBatalhas.HistoricoBatalha;
import avanade.api.domain.historicoBatalhas.HistoricoBatalhaRepository;
import avanade.api.domain.historicoBatalhas.dto.DadosCadastroHistoricoBatalha;
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
    @Autowired
    private IniciativaRepository iniciativaRepository;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private AtaqueRepository ataqueRepository;
    @Autowired
    private DefesaRepository defesaRepository;
    @Autowired
    private DanoRepository danoRepository;
    @Autowired
    private PontosVidaRepository pontosVidaRepository;
    Random random = new Random();

    public DadosDetalhamentoBatalha gerarBatalha(DadosCadastroBatalha dados) {
        var usuario = usuarioRepository.findById(dados.usuario_id()).get();
        if (usuario.getPersonagem_id() == null) {
            throw new ValidacaoException("Usuario precisa escolher personagem antes de procurar uma batalha!");
        }
        var emBatalha = batalhaRepository.usuarioEmBatalha(usuario.getId());
        if (emBatalha != null) {
            throw new ValidacaoException("Usuario com batalha em andamento!");
        }
        var personagemUsuario = personagemRepository.findById(usuario.getPersonagem_id()).get();
        var personagemComputador = personagemRepository.buscarPersonagemAleatorio(personagemUsuario.getId());
        var batalha = new Batalha(new DadosCadastroBatalha(dados.usuario_id(), personagemUsuario.getId(), personagemComputador.getId(), LocalDateTime.now(), null, true, null));
        batalhaRepository.save(batalha);
        var historicobatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), null, personagemUsuario.getNome(), Acao.GERAR_BATALHA, null, null, null, null, null, null, LocalDateTime.now()));
        historicoBatalhaRepository.save(historicobatalha);
        var pontosVida = new PontosVida(new DadosCadastroPontosVida(batalha.getId(), batalha.getUsuario_id(), 0, personagemUsuario.getVida(), true));
        pontosVidaRepository.save(pontosVida);
        pontosVida = new PontosVida(new DadosCadastroPontosVida(batalha.getId(), batalha.getPersonComputador_id(), 0, personagemComputador.getVida(), true));
        pontosVidaRepository.save(pontosVida);
        return new DadosDetalhamentoBatalha(batalha);
    }

    public DadosDetalhamentoIniciativa iniciarBatalha(Long id) {
        var numSorteadoUsuario = 0;
        var numSorteadoComputador = 0;
        Iniciativa iniciativa;
        var hbbatalha = iniciativaRepository.buscarIniciado(id);
        /*if (hbbatalha != null) {
            throw new ValidacaoException("Batalha já foi iniciada!");
        }*/
        var faceDoDado = 20;
        var batalha = batalhaRepository.findById(id).get();
        var personagemUsuario = personagemRepository.findById(batalha.getPersonUsuario_id()).get();
        var personagemComputador = personagemRepository.findById(batalha.getPersonComputador_id()).get();

        while (numSorteadoUsuario == numSorteadoComputador) {
            numSorteadoUsuario = random.nextInt(faceDoDado) + 1;
            numSorteadoComputador = random.nextInt(faceDoDado) + 1;
        }
        if (numSorteadoUsuario > numSorteadoComputador) {
            var turno = new Turno(new DadosCadastroTurno(batalha.getId(), personagemUsuario.getId()));
            iniciativa = new Iniciativa(new DadosCadastroIniciatica(batalha.getId(), personagemUsuario.getId(), personagemUsuario.getNome(), true, numSorteadoUsuario));
            iniciativaRepository.save(iniciativa);
            iniciativa = new Iniciativa(new DadosCadastroIniciatica(batalha.getId(), personagemComputador.getId(), personagemComputador.getNome(), true, numSorteadoComputador));
            iniciativaRepository.save(iniciativa);
            turnoRepository.save(turno);
        } else {
            var turno = new Turno(new DadosCadastroTurno(batalha.getId(), personagemComputador.getId()));
            iniciativa = new Iniciativa(new DadosCadastroIniciatica(batalha.getId(), personagemUsuario.getId(), personagemUsuario.getNome(), true, numSorteadoUsuario));
            iniciativaRepository.save(iniciativa);
            iniciativa = new Iniciativa(new DadosCadastroIniciatica(batalha.getId(), personagemComputador.getId(), personagemComputador.getNome(), true, numSorteadoComputador));
            iniciativaRepository.save(iniciativa);
            turnoRepository.save(turno);
        }
        var novoturno = turnoRepository.findByIdbatalhaId(id);
        var hBUsuario = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), novoturno.getId(), personagemUsuario.getNome(), Acao.INICIAR_BATALHA, (long) numSorteadoUsuario, null, null, null, null, null, LocalDateTime.now()));
        historicoBatalhaRepository.save(hBUsuario);
        var hBComputador = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(batalha.getId(), novoturno.getId(), personagemComputador.getNome(), Acao.INICIAR_BATALHA, (long) numSorteadoComputador, null, null, null, null, null, LocalDateTime.now()));
        historicoBatalhaRepository.save(hBComputador);
        return new DadosDetalhamentoIniciativa(iniciativa);
    }


    public DadosAtaqueBatalha ataque(Long id) {
        var faceDoDado = 12;
        var turno = turnoRepository.findByIdbatalhaId(id);
        var personagem = personagemRepository.findById(turno.getPersonagem_id()).get();
        var numSorteado = random.nextInt(faceDoDado) + 1;
        numSorteado += personagem.getForca() + personagem.getAgilidade();
        var ataque = new Ataque(new DadosAtaqueBatalha(turno.getId(), personagem.getId(), numSorteado, personagem.getForca(), personagem.getAgilidade()));
        ataqueRepository.save(ataque);
        var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(id, turno.getId(), personagem.getNome(), Acao.ATACAR, null, (long) numSorteado, null, null, null, null, LocalDateTime.now()));
        historicoBatalhaRepository.save(historicoBatalha);
        return new DadosAtaqueBatalha(ataque);
    }

    public DadosDefesaBatalha defesa(Long id) {
        var faceDoDado = 12;
        var turno = turnoRepository.findByIdbatalhaId(id);
        var iniciativa = iniciativaRepository.findByIddefesa(id, turno.getPersonagem_id());
        var personagem = personagemRepository.findById(iniciativa.getPersonagem_id()).get();
        var numSorteado = random.nextInt(faceDoDado) + 1;
        var ataque = ataqueRepository.findById(turno.getId()).get();
        while (ataque.getPontos() == numSorteado) {
            numSorteado = random.nextInt(faceDoDado) + 1;
        }
        numSorteado += personagem.getDefesa() + personagem.getAgilidade();
        var defesa = new Defesa(new DadosDefesaBatalha(turno.getId(), personagem.getId(), numSorteado, personagem.getDefesa(), personagem.getAgilidade()));
        defesaRepository.save(defesa);
        var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(id, turno.getId(), personagem.getNome(), Acao.DEFENDER, null, null, (long) numSorteado, null, null, null, LocalDateTime.now()));
        historicoBatalhaRepository.save(historicoBatalha);

        return new DadosDefesaBatalha(defesa);
    }


    public DadosDanoBatalha calculardano(Long id) {
        var turno = turnoRepository.findByIdbatalhaId(id);
        var ataque = ataqueRepository.findById(turno.getId()).get();
        var defesa = defesaRepository.findById(turno.getId()).get();
        var personagem = personagemRepository.findById(turno.getPersonagem_id()).get();
        var pontosVida = pontosVidaRepository.findByIdbatalhaId(id, defesa.getPersonagem_id());
        if (ataque.getPontos() <= defesa.getPontos()) {
            var dano = new Dano(new DadosDanoBatalha(turno.getId(), personagem.getId(), (int) 0, personagem.getQuantidadeDeDados(), personagem.getFacesDoDado()));
            danoRepository.save(dano);
            var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(id, turno.getId(), personagem.getNome(), Acao.CALCULO_DE_DANO, null, null, null, (long) 0, null, "A DEFESA CONSEGUIU SE DEFENDER, INICIADO UM NOVO TURNO", LocalDateTime.now()));
            historicoBatalhaRepository.save(historicoBatalha);
            var iniciativas = iniciativaRepository.buscarIniciado(id);
            for (Iniciativa iniciativa : iniciativas) {
                iniciativa.atualizar(iniciativa.getBatalha_id());
            }
            iniciarBatalha(id);
            return new DadosDanoBatalha(dano);
        }
        var valorDano = jogarDado(personagem.getQuantidadeDeDados(), personagem.getFacesDoDado());
        valorDano += personagem.getForca();
        var dano = new Dano(new DadosDanoBatalha(turno.getId(), personagem.getId(), valorDano, personagem.getQuantidadeDeDados(), personagem.getFacesDoDado()));
        danoRepository.save(dano);

        if (dano.getValorDano() >= pontosVida.getPontosVida()) {
            pontosVida.atualizar(dano.getValorDano(),false);
            var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(id, turno.getId(), personagem.getNome(), Acao.PONTO_VIDA, null, null, null, (long)dano.getValorDano(), (long)pontosVida.getPontosVida(), "GAME OVER!!!!!!", LocalDateTime.now()));
            historicoBatalhaRepository.save(historicoBatalha);
        }
        var historicoBatalha = new HistoricoBatalha(new DadosCadastroHistoricoBatalha(id, turno.getId(), personagem.getNome(), Acao.PONTO_VIDA, null, null, null, (long)dano.getValorDano(), (long)pontosVida.getPontosVida(), "O ATACAQUE CAUSOU DANOS", LocalDateTime.now()));
        historicoBatalhaRepository.save(historicoBatalha);
        pontosVida.atualizar(dano.getValorDano(),true);
        var iniciativas = iniciativaRepository.buscarIniciado(id);
        for (Iniciativa iniciativa : iniciativas) {
            iniciativa.atualizar(iniciativa.getBatalha_id());
        }
        iniciarBatalha(id);
        return new DadosDanoBatalha(dano);
    }

    private void calcularpontosvida(int valorDano) {
    }

    private int jogarDado(int quantidadeDados, int faceDoDado) {
        var novoNumero = 0;
        for (int i = 0; i < quantidadeDados; i++) {
            Random random = new Random();
            var numero = random.nextInt(faceDoDado) + 1;
            novoNumero += numero;
        }
        return novoNumero;
    }
}
