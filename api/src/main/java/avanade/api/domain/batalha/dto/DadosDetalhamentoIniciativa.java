package avanade.api.domain.batalha.dto;

import avanade.api.domain.batalha.Iniciativa;

public record DadosDetalhamentoIniciativa(Long batalha_id, Long personagem_id, String personagem, int pontos) {
    public DadosDetalhamentoIniciativa (Iniciativa iniciativa){
        this(iniciativa.getBatalha_id(),iniciativa.getPersonagem_id(), iniciativa.getPersonagem(), iniciativa.getPontos());
    }
}
