package avanade.api.controller;

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
    public void Cadastrar(@RequestBody DadosCadastroPersonagem dados) {
        repository.save(new Personagem(dados));
    }
    @GetMapping
    public Page<DadosListagemPersonagem> Listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPersonagem::new);
    }
}
