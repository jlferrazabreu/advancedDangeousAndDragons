package avanade.api.domain.dto.usuario;


import avanade.api.domain.usuario.Usuario;

public record DadosDetalhamentoUsuario(Long id,String login, String senha) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }
}
