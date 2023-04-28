package avanade.api.domain.batalha;

import avanade.api.domain.dto.historicoBatalha.DadosCadastroHistoricoBatalha;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="historicoBatalhas")
@Entity(name="HistoricoBatalha")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoricoBatalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long batalha_id;
    @NotBlank
    private String jogador;
    @NotBlank
    private String personagem;
    @NotBlank
    private String acao;
    private String proximaAcao;
    private int numSorteado;
    private int dano;
    private int pontosVida;
    private LocalDateTime data;

    public HistoricoBatalha(DadosCadastroHistoricoBatalha dados) {
        this.batalha_id = dados.batalha_id();
        this.jogador = dados.jogador();
        this.personagem = dados.personagem();
        this.acao = dados.acao();
        this.proximaAcao = dados.proximaAcao();
        this.numSorteado = dados.numSorteado();
        this.dano = dados.dano();
        this.pontosVida = dados.pontosVida();
        this.data = dados.data();
    }
}
