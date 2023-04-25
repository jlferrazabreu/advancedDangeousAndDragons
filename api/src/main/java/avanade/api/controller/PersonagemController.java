package avanade.api.controller;

import avanade.api.dto.personagem.DadosCadastroPersonagem;
import avanade.api.model.personagem.Personagem;
import avanade.api.model.personagem.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
