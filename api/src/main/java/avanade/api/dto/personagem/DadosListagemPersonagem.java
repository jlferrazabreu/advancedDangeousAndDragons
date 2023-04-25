package avanade.api.dto.personagem;

import avanade.api.model.personagem.Personagem;
import avanade.api.model.personagem.TipoPersonagem;

public record DadosListagemPersonagem(Long id,TipoPersonagem tipoPersonagem, String nome, int vida, int forca, int defesa, int agilidade) {
    public DadosListagemPersonagem(Personagem personagem) {
        this(personagem.getId(),
                personagem.getTipoPersonagem(),
                personagem.getNome(),
                personagem.getVida(),
                personagem.getForca(),
                personagem.getDefesa(),
                personagem.getAgilidade()
                );
    }
}
