package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosCadastroPontosVida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="pontosVida")
@Entity(name="PontosVida")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PontosVida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long batalha_id;
    private Long personagem_id;
    private int valorDano;
    private int pontosVida;
    private Boolean ativo;

    public PontosVida(DadosCadastroPontosVida dados) {
        this.batalha_id = dados.batalha_id();
        this.personagem_id = dados.personagem_id();
        this.valorDano = dados.valorDano();
        this.pontosVida = dados.pontosVida();
        this.ativo = dados.ativo();
    }

    public void atualizar(int valorDano, Boolean ativo) {
        this.valorDano = valorDano;
        this.pontosVida = pontosVida - valorDano;
        this.ativo = ativo;
    }
}
