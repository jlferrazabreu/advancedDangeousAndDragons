package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.Iniciativa;

public record DadosAtualizaIniciativa(boolean ativo) {
    public DadosAtualizaIniciativa(Iniciativa iniciativa){
        this(iniciativa.getAtivo());
    }
}
