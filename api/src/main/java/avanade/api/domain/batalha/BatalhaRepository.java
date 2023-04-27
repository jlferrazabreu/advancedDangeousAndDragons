package avanade.api.domain.batalha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BatalhaRepository extends JpaRepository<Batalha, Long> {
    @Query("select b from Batalha b where usuario_id = :id and ativo = 1")
    Batalha usuarioEmBatalha(Long id);
}
