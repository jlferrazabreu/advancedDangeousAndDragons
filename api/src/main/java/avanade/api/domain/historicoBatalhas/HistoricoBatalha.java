package avanade.api.domain.historicoBatalhas;

import avanade.api.domain.historicoBatalhas.dto.DadosCadastroHistoricoBatalha;
import jakarta.persistence.*;
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
    private Long turno_id;
    private String personagem;
    private Acao acao;
    private Long pontosInicio;
    private Long pontosAtaque;
    private Long pontosDefesa;
    private Long qtdDano;
    private Long pontosVida;
    private String descricao;
    private LocalDateTime data;

    public HistoricoBatalha(DadosCadastroHistoricoBatalha dados) {
        this.batalha_id = dados.batalha_id();
        this.turno_id = dados.turno_id();
        this.personagem = dados.personagem();
        this.acao = dados.acao();
        this.pontosInicio = dados.pontosInicio();
        this.pontosAtaque = dados.pontosAtaque();
        this.pontosDefesa = dados.pontosDefesa();
        this.qtdDano = dados.qtdDano();
        this.pontosVida = dados.pontosVida();
        this.descricao = dados.descricao();
        this.data = dados.data();
    }
}
