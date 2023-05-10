package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosDefesaBatalha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="defesas")
@Entity(name="Defesa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Defesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long turno_id;
    private Long personagem_id;
    private  int pontos;
    private int defesa;
    private int agilidade;

    public Defesa(DadosDefesaBatalha dados) {
        this.turno_id = dados.turno_id();
        this.personagem_id = dados.personagem_id();
        this.pontos = dados.pontos();
        this.defesa = dados.defesa();
        this.agilidade = dados.agilidade();
    }
}
