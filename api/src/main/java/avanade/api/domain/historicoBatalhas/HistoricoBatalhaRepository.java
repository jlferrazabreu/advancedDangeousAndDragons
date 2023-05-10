package avanade.api.domain.historicoBatalhas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoBatalhaRepository extends JpaRepository<HistoricoBatalha, Long> {
    /*@Query("select h from HistoricoBatalha h where batalhaId = :id and acao = 'INICIAR' order by h.id limit 1")
    HistoricoBatalha buscarIniciado(Long id);
    @Query("select h from HistoricoBatalha h where batalhaId = :id and proximaAcao = 'ATACAR' order by id desc limit 1")
    HistoricoBatalha buscarAtacante(Long id);
    @Query("select h from HistoricoBatalha h where batalhaId = :id and proximaAcao = 'DEFENDER' order by id desc limit 1")
    HistoricoBatalha buscarDefensor(Long id);
    @Query("select h from HistoricoBatalha h where batalhaId = :id and proximaAcao = 'DANO' and acao = 'ATACAR' order by id desc limit 1")
    HistoricoBatalha buscarAtacanteDano(Long id);
    @Query("select h from HistoricoBatalha h where batalhaId = :id and proximaAcao = 'DANO' and acao = 'DEFENDER' order by id desc limit 1")
    HistoricoBatalha buscarDefensorDano(Long id);
    @Query("select h from HistoricoBatalha h where batalhaId = :id and proximaAcao = 'PONTOS_VIDA' and acao = 'DANO' order by id desc limit 1")
    HistoricoBatalha buscarAtacantePontosVida(Long id);
    @Query("select h from HistoricoBatalha h where batalhaId = :id and proximaAcao = 'DANO' and acao = 'DEFENDER' order by id desc limit 1")
    HistoricoBatalha buscarDefensorPontosVida(Long id);*/
}
