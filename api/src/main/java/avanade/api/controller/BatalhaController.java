package avanade.api.controller;

import avanade.api.domain.dto.batalha.DadosCadastroBatalha;
import avanade.api.infra.service.GerenciadorDeBatalha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity iniciarBatalha(@PathVariable Long id){
        var dto = gerenciador.iniciarBatalha(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("ataque/{id}")
    @Transactional
    public ResponseEntity ataque(@PathVariable Long id){
        var dto = gerenciador.ataque(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("defesa/{id}")
    @Transactional
    public ResponseEntity defesa(@PathVariable Long id){
        var dto = gerenciador.defesa(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping("calculardano/{id}")
    @Transactional
    public ResponseEntity calculardano(@PathVariable Long id){
        var dto = gerenciador.calculardano(id);
        return ResponseEntity.ok(dto);
    }
    @PutMapping("calcularpontosvida/{id}")
    @Transactional
    public ResponseEntity calcularpontosvida(@PathVariable Long id){
        var dto = gerenciador.calcularpontosvida(id);
        return ResponseEntity.ok(dto);
    }
}
