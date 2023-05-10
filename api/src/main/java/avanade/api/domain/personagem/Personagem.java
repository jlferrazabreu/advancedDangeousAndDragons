package avanade.api.domain.personagem;

import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;
import avanade.api.domain.personagem.dto.DadosCadastroPersonagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="personagens")
@Entity(name="Personagem")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPersonagem tipoPersonagem;
    @Column(unique=true)
    private String nome;
    private int vida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int quantidadeDeDados;
    private int facesDoDado;
    private Boolean ativo;

    public Personagem(DadosCadastroPersonagem dados) {
        this.tipoPersonagem = dados.tipoPersonagem();
        this.nome = dados.nome();
        this.vida = dados.vida();
        this.forca = dados.forca();
        this.defesa = dados.defesa();
        this.agilidade = dados.agilidade();
        this.quantidadeDeDados = dados.quantidadeDeDados();
        this.facesDoDado = dados.facesDoDado();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoPersonagem dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.vida() != 0) {
            this.vida = dados.vida();
        }
        if (dados.forca() != 0) {
            this.forca = dados.forca();
        }
        if (dados.defesa() != 0) {
            this.defesa = dados.defesa();
        }
        if (dados.agilidade() != 0) {
            this.agilidade = dados.agilidade();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
