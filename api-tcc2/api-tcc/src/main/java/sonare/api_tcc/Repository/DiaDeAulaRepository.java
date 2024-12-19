package sonare.api_tcc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sonare.api_tcc.Entity.DiaDeAulaEntity;
import sonare.api_tcc.Entity.TurmaEntity;
import sonare.api_tcc.Enum.DiaDaSemana;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface DiaDeAulaRepository extends JpaRepository<DiaDeAulaEntity, Long> {
    List<DiaDeAulaEntity> findByDiaDaSemanaAndHorarioInicioAndHorarioFimAndTurma(DiaDaSemana diaDaSemana, LocalTime horarioInicio, LocalTime horarioFim, TurmaEntity turma);

    List<DiaDeAulaEntity> findByTurma (TurmaEntity turma);
}
