package avanade.api.domain.personagem.dto;

import avanade.api.domain.personagem.Personagem;
import avanade.api.domain.personagem.TipoPersonagem;

public record DadosDetalhamentoPersonagem(Long id, TipoPersonagem tipoPersonagem, String nome, Integer vida, Integer forca, Integer defesa, Integer agilidade, Integer quantidadeDeDados, Integer facesDoDado) {
    public DadosDetalhamentoPersonagem(Personagem personagem) {
        this(personagem.getId(), personagem.getTipoPersonagem(),personagem.getNome(), personagem.getVida(), personagem.getForca(), personagem.getDefesa(), personagem.getAgilidade(), personagem.getQuantidadeDeDados(), personagem.getQuantidadeDeDados());
    }
}
