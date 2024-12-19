package sonare.api_tcc.DTO.Turma;

import sonare.api_tcc.DTO.DiaDeAula.RegistroDiaDeAulaDTO;
import sonare.api_tcc.Entity.ModuloEntity;

import java.util.List;

public record RegistroTurmaDTO(
        String nome,
        Long cursoId,
        Long moduloId
) {
}
