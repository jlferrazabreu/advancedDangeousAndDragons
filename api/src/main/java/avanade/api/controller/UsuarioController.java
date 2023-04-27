package avanade.api.controller;

import avanade.api.domain.dto.usuario.DadosCadastroUsuario;
import avanade.api.domain.dto.usuario.DadosDetalhamentoEscolhePersonagem;
import avanade.api.domain.dto.usuario.DadosEscolherPersonagem;
import avanade.api.infra.service.GerenciadorDeUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private GerenciadorDeUsuario gerenciador;

    @PostMapping()
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var dto = gerenciador.cadastrar(dados);
        var uri = uriBuilder.path("/personagens/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity escolherPersonagem(@RequestBody @Valid DadosEscolherPersonagem dados, @PathVariable Long id){
        var dto = gerenciador.escolherPersonagem(dados,id);
        return ResponseEntity.ok(new DadosDetalhamentoEscolhePersonagem(dto.login(), dto.personagem()));
    }
}
