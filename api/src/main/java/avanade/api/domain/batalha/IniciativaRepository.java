package avanade.api.domain.batalha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IniciativaRepository extends JpaRepository<Iniciativa, Long> {
    @Query("select i from Iniciativa i where i.batalha_id = :id and ativo = 1")
    List<Iniciativa> buscarIniciado(Long id);
    @Query("""
    select i from Iniciativa i 
    where batalha_id = :id 
    and personagem_id <>  :personagem_id
    order by id desc limit 1
    """)
    Iniciativa findByIddefesa(Long id,Long personagem_id);
}
