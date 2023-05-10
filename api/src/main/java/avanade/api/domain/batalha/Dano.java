package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosDanoBatalha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="danos")
@Entity(name="Dano")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long turno_id;
    private Long personagem_id;
    private  int valorDano;
    private int qtdDado;
    private int faceDado;

    public Dano(DadosDanoBatalha dados) {
        this.turno_id = dados.turno_id();
        this.personagem_id = dados.personagem_id();
        this.valorDano = dados.valorDano();
        this.qtdDado = dados.qtdDado();
        this.faceDado = dados.faceDado();
    }
}
