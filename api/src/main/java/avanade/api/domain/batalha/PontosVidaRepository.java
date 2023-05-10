package avanade.api.domain.batalha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PontosVidaRepository extends JpaRepository<PontosVida, Long> {
    @Query("select p from PontosVida p where p.batalha_id = :id and p.personagem_id = :personagem_id and ativo = 1")
    PontosVida findByIdbatalhaId(Long id,Long personagem_id);
}
