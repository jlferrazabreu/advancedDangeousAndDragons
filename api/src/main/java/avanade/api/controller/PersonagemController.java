package avanade.api.controller;

import avanade.api.domain.personagem.dto.DadosAtualizacaoPersonagem;
import avanade.api.domain.personagem.dto.DadosCadastroPersonagem;
import avanade.api.domain.personagem.dto.DadosListagemPersonagem;
import avanade.api.infra.service.personagem.*;
import jakarta.validation.Valid;
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
    private DetalharPersonagemService personagemService;
    @Autowired
    private CadastroPersonagemService cadastroPersonagemService;
    @Autowired
    private ListarPersonagemService listarPersonagemService;
    @Autowired
    private AtualizarPersonagemService atualizarPersonagemService;
    @Autowired
    private ExcluirPersonagemService excluirPersonagemService;
    @Autowired
    private DetalharPersonagemService detalharPersonagemService;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPersonagem dados, UriComponentsBuilder uriBuilder) {
        var dto = cadastroPersonagemService.cadastrar(dados);
        var uri = uriBuilder.path("/personagens/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemPersonagem>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        var dto = listarPersonagemService.listar(paginacao);
        return ResponseEntity.ok(dto);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPersonagem dados) {
        var dto = atualizarPersonagemService.atualizar(dados);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable @Valid Long id){
        excluirPersonagemService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable @Valid Long id){
        var dto = detalharPersonagemService.detalhar(id);
        return ResponseEntity.ok(dto);
    }
}
