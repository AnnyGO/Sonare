package sonare.api_tcc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sonare.api_tcc.Entity.CursoEntity;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity,Long> {
    @Query(value = "SELECT c.*, COUNT(t.curso_id) as conta FROM cursos c JOIN turma t ON c.curso_id = t.curso_id GROUP BY c.curso_id HAVING COUNT(t.curso_id) <= :quantidadeMaxima", nativeQuery = true)
    List<CursoEntity> findByQuantidadeMaximaTurmas(@Param("quantidadeMaxima") int quantidadeMaxima);
}
