package avanade.api.domain.batalha;

import avanade.api.domain.dto.batalha.DadosCadastroBatalha;
import avanade.api.domain.dto.batalha.DadosEncerramentoBatalha;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="batalhas")
@Entity(name="Batalha")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuario_id;
    private Long personagem_usuario_id;
    private Long personagem_id;
    private Boolean ativo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;

    public Batalha(DadosCadastroBatalha dados, Long personagem_id, Long personagem_usuario_id) {
        this.usuario_id = dados.usuario_id();
        this.personagem_usuario_id = personagem_usuario_id;
        this.personagem_id = personagem_id;
        this.ativo = true;
        this.dataInicio = LocalDateTime.now();
    }
    /*public Batalha(DadosEncerramentoBatalha dados) {
        this.ativo = dados.ativo();
        this.dataFinal = dados.dataFinal();
    }*/

    public void atualizarBatalha(DadosEncerramentoBatalha dados) {
            this.ativo = false;
            this.dataFinal = LocalDateTime.now();
    }
}
