package avanade.api.controller;

import avanade.api.domain.dto.batalha.DadosCadastroBatalha;
import avanade.api.infra.service.GerenciadorDeBatalha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batalhas")
public class BatalhaController {
    @Autowired
    private GerenciadorDeBatalha gerenciador;
    @PostMapping
    @Transactional
    public ResponseEntity gerarBatalha(@RequestBody DadosCadastroBatalha dados){
        var dto = gerenciador.gerarBatalha(dados);
        return ResponseEntity.ok(dto);
    }
}
