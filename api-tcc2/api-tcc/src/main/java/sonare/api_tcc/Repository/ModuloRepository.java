package sonare.api_tcc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sonare.api_tcc.Entity.ModuloEntity;

import java.util.List;

@Repository
public interface ModuloRepository extends JpaRepository<ModuloEntity, Long> {
    List<ModuloEntity> findByCursoCursoId(Long cursoId);
}

