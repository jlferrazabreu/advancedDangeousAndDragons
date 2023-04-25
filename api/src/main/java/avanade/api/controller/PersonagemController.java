package avanade.api.controller;

import avanade.api.dto.personagem.DadosCadastroPersonagem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personagens")
public class PersonagemController {
    @PostMapping
    public void Cadastrar(@RequestBody DadosCadastroPersonagem dados) {
        System.out.println(dados);
    }
}
