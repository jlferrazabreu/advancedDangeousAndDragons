package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosAtaqueBatalha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="ataques")
@Entity(name="Ataque")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ataque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long turno_id;
    private Long personagem_id;
    private int pontos;
    private int forca;
    private int agilidade;

    public Ataque(DadosAtaqueBatalha dados) {
        this.turno_id = dados.turno_id();
        this.personagem_id = dados.personagem_id();
        this.pontos = dados.pontos();
        this.forca = dados.forca();
        this.agilidade = dados.agilidade();
    }
}
