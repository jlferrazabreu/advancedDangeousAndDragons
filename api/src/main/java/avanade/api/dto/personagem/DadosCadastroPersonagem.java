package avanade.api.dto.personagem;

import avanade.api.model.Enuns.TipoPersonagem;

public record DadosCadastroPersonagem(TipoPersonagem tipoPersonagem, String nome, int vida, int forca, int defesa, int agilidade, int quantidadeDeDados, int FacesDoDado) {
}
