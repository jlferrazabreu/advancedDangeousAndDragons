package avanade.api.infra.service.personagem;

import avanade.api.domain.personagem.PersonagemRepository;
import avanade.api.domain.personagem.dto.DadosListagemPersonagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPersonagemService {
    @Autowired
    private PersonagemRepository repository;
    public Page<DadosListagemPersonagem> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPersonagem::new);
        return page;
    }
}
