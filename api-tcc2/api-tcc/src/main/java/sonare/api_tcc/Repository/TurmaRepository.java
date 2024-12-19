package sonare.api_tcc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sonare.api_tcc.Entity.TurmaEntity;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity,Long> {
    @Query(value = "SELECT * FROM turma WHERE curso_id = :cursoId", nativeQuery = true)
    List<TurmaEntity> findByCursoId(@Param("cursoId") Long cursoId);

}
