package avanade.api.controller;

import avanade.api.domain.dto.personagem.DadosAtualizacaoPersonagem;
import avanade.api.domain.dto.personagem.DadosCadastroPersonagem;
import avanade.api.domain.dto.personagem.DadosListagemPersonagem;
import avanade.api.infra.service.GerenciadorDePersonagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("personagens")
public class PersonagemController {
    @Autowired
    private GerenciadorDePersonagem gerenciador;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroPersonagem dados, UriComponentsBuilder uriBuilder) {
        var dto = gerenciador.cadastrar(dados);
        var uri = uriBuilder.path("/personagens/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemPersonagem>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        var dto = gerenciador.listar(paginacao);
        return ResponseEntity.ok(dto);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPersonagem dados) {
        var dto = gerenciador.atualizar(dados);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        gerenciador.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var dto = gerenciador.detalhar(id);
        return ResponseEntity.ok(dto);
    }
}
