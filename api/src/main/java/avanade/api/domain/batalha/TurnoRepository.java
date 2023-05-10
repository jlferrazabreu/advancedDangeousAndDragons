package avanade.api.domain.batalha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    @Query("select t from Turno t where batalha_id = :id order by id desc limit 1")
    Turno findByIdbatalhaId(Long id);
}
