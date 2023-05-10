package avanade.api.domain.personagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Page<Personagem> findAllByAtivoTrue(Pageable paginacao);
    @Query("select p from Personagem p where p.ativo =1 and p.id <> :id order by rand() limit 1")
    Personagem buscarPersonagemAleatorio(Long id);

    boolean existsByNome(String nome);
}
