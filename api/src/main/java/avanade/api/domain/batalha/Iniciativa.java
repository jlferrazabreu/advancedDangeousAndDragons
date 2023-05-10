package avanade.api.domain.batalha;

import avanade.api.domain.batalha.dto.DadosCadastroIniciatica;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="iniciativas")
@Entity(name="Iniciativa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Iniciativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long batalha_id;
    private long personagem_id;
    private String personagem;
    private Boolean ativo;
    private int pontos;

    public Iniciativa(DadosCadastroIniciatica dados) {
        this.batalha_id = dados.batalha_id();
        this.personagem_id = dados.personagem_id();
        this.personagem = dados.personagem();
        this.ativo = dados.ativo();
        this.pontos = dados.pontos();
    }

    public void atualizar(Long batalha_id){
        this.ativo = false;
    }
}
