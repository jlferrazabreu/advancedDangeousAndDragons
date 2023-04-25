package avanade.api.model.personagem;

import avanade.api.dto.personagem.DadosCadastroPersonagem;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="personagens")
@Entity(name="Personagem")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TipoPersonagem tipoPersonagem;
    private String nome;
    private int vida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int quantidadeDeDados;
    private int facesDoDado;

    public Personagem(DadosCadastroPersonagem dados) {
        this.tipoPersonagem = dados.tipoPersonagem();
        this.nome = dados.nome();
        this.vida = dados.vida();
        this.forca = dados.forca();
        this.defesa = dados.defesa();
        this.agilidade = dados.agilidade();
        this.quantidadeDeDados = dados.quantidadeDeDados();
        this.facesDoDado = dados.facesDoDado();
    }
}
