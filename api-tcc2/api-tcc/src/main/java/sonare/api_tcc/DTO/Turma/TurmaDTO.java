package sonare.api_tcc.DTO.Turma;

import com.fasterxml.jackson.annotation.JsonFormat;
import sonare.api_tcc.Entity.AlunoEntity;
import sonare.api_tcc.Entity.CursoEntity;
import sonare.api_tcc.Entity.DiaDeAulaEntity;
import sonare.api_tcc.Entity.ModuloEntity;

import java.time.LocalDate;
import java.util.List;

public record TurmaDTO(
        Long turmaId,
        String nome,
        ModuloEntity modulo,
        CursoEntity curso,
        @JsonFormat(pattern = "dd/MM/YYYY")
        LocalDate dataCriacao,
        List<DiaDeAulaEntity>diasDeAula,
        List<AlunoEntity> alunos
) {
}