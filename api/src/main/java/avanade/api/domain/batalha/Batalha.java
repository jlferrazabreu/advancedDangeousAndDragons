package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosCadastroBatalha;
import avanade.api.domain.batalha.dto.DadosEncerramentoBatalha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="batalhas")
@Entity(name="Batalha")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuario_id;
    private Long personUsuario_id;
    private Long personComputador_id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;
    private Boolean ativo;
    private String vencedor;

    public Batalha(DadosCadastroBatalha dados) {
        this.usuario_id = dados.usuario_id();
        this.personUsuario_id = dados.personUsuario_id();
        this.personComputador_id = dados.personComputador_id();
        this.dataInicio = dados.dataInicio();
        this.dataFinal = dados.dataFinal();
        this.ativo = dados.ativo();
        this.vencedor = dados.vencedor();
    }

    public void atualizarBatalha(DadosEncerramentoBatalha dados) {
            this.ativo = false;
            this.dataFinal = LocalDateTime.now();
    }
}
