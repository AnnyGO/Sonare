package sonare.api_tcc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sonare.api_tcc.Entity.AlunoEntity;
import sonare.api_tcc.Entity.TurmaEntity;
import sonare.api_tcc.Enum.AlunoStatus;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity,Long> {
    @Query(value = "SELECT * FROM aluno WHERE status = :status", nativeQuery = true)
    List<AlunoEntity> findByStatus(@Param("status") String status);

    List<AlunoEntity> findByTurma (TurmaEntity turma);
}
