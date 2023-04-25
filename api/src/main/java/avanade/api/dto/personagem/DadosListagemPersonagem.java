package avanade.api.dto.personagem;

import avanade.api.model.personagem.Personagem;
import avanade.api.model.personagem.TipoPersonagem;

public record DadosListagemPersonagem(TipoPersonagem tipoPersonagem, String nome, int vida, int forca, int defesa, int agilidade) {
    public DadosListagemPersonagem(Personagem personagem) {
        this(personagem.getTipoPersonagem(),
                personagem.getNome(),
                personagem.getVida(),
                personagem.getForca(),
                personagem.getDefesa(),
                personagem.getAgilidade()
                );}
}
