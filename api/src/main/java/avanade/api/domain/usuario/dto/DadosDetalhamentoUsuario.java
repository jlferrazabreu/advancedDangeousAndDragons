package avanade.api.domain.usuario.dto;


import avanade.api.domain.usuario.Usuario;

public record DadosDetalhamentoUsuario(Long id,String login, String senha) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }
}
