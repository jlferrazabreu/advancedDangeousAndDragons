package avanade.api.dto.personagem;

import avanade.api.model.personagem.Personagem;
import avanade.api.model.personagem.TipoPersonagem;

public record DadosDetalhamentoPersonagem(Long id, TipoPersonagem tipoPersonagem, String nome, Integer vida, Integer forca, Integer defesa, Integer agilidade, Integer quantidadeDeDados, Integer facesDoDado) {
    public DadosDetalhamentoPersonagem(Personagem personagem) {
        this(personagem.getId(), personagem.getTipoPersonagem(),personagem.getNome(), personagem.getVida(), personagem.getForca(), personagem.getDefesa(), personagem.getAgilidade(), personagem.getQuantidadeDeDados(), personagem.getQuantidadeDeDados());
    }
}
