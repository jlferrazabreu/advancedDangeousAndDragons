package avanade.api.controller;

import avanade.api.dto.personagem.DadosAtualizacaoPersonagem;
import avanade.api.dto.personagem.DadosCadastroPersonagem;
import avanade.api.dto.personagem.DadosListagemPersonagem;
import avanade.api.model.personagem.Personagem;
import avanade.api.model.personagem.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("personagens")
public class PersonagemController {
    @Autowired
    private PersonagemRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPersonagem dados) {
        repository.save(new Personagem(dados));
    }
    @GetMapping
    public Page listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPersonagem::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoPersonagem dados) {
        var personagem = repository.getReferenceById(dados.id());
        personagem.atualizarInformacoes(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public  void excluir(@PathVariable Long id){
        var personagem = repository.getReferenceById(id);
        personagem.excluir();
    }
}
