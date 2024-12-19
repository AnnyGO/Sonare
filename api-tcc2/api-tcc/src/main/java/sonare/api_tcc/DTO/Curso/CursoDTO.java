package sonare.api_tcc.DTO.Curso;

import com.fasterxml.jackson.annotation.JsonFormat;
import sonare.api_tcc.Entity.ModuloEntity;

import java.time.LocalDate;
import java.util.List;

public record CursoDTO(
        Long cursoId,
        String nome,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataCriacao,
        List<ModuloEntity> modulos
) {
}
