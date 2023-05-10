package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosCadastroTurno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="turnos")
@Entity(name="Turno")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long batalha_id;
    @NotNull
    private Long personagem_id;

    public Turno(DadosCadastroTurno dados) {
        this.batalha_id = dados.batalha_id();
        this.personagem_id = dados.personagem_id();
    }
}
