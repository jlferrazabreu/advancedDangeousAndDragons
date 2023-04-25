package avanade.api.controller;

import avanade.api.dto.personagem.DadosAtualizacaoPersonagem;
import avanade.api.dto.personagem.DadosCadastroPersonagem;
import avanade.api.dto.personagem.DadosDetalhamentoPersonagem;
import avanade.api.dto.personagem.DadosListagemPersonagem;
import avanade.api.model.personagem.Personagem;
import avanade.api.model.personagem.PersonagemRepository;
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
    private PersonagemRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroPersonagem dados, UriComponentsBuilder uriBuilder) {
        var personagem = new Personagem(dados);
        repository.save(personagem);

        var uri = uriBuilder.path("/personagens/{id}").buildAndExpand(personagem.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPersonagem(personagem));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemPersonagem>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPersonagem::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPersonagem dados) {
        var personagem = repository.getReferenceById(dados.id());
        personagem.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPersonagem(personagem));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var personagem = repository.getReferenceById(id);
        personagem.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var personagem = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPersonagem(personagem));
    }
}
