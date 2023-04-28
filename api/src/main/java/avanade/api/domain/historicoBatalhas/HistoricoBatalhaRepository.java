package avanade.api.domain.historicoBatalhas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoricoBatalhaRepository extends JpaRepository<HistoricoBatalha, Long> {
    @Query("select h from HistoricoBatalha h where batalha_id = :id and acao = 'INICIAR' order by h.id limit 1")
    HistoricoBatalha buscarIniciado(Long id);
    @Query("select h from HistoricoBatalha h where batalha_id = :id and proximaAcao = 'ATACAR' order by id desc limit 1")
    HistoricoBatalha buscarAtacante(Long id);
    @Query("select h from HistoricoBatalha h where batalha_id = :id and proximaAcao = 'DEFENDER' order by id desc limit 1")
    HistoricoBatalha buscarDefensor(Long id);
}
