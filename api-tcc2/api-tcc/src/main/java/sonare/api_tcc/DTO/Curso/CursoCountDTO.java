package sonare.api_tcc.DTO.Curso;

import sonare.api_tcc.Entity.CursoEntity;

public record CursoCountDTO(
        CursoEntity curso,
        Long conta
) {
}
