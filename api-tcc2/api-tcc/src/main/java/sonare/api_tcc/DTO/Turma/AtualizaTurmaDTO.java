package sonare.api_tcc.DTO.Turma;

import sonare.api_tcc.DTO.DiaDeAula.DiaDeAulaDTO;

import java.util.List;

public record AtualizaTurmaDTO(
        String nome,
        Long cursoId,
        List<DiaDeAulaDTO> diasDeAula, // IDs dos dias de aula
        List<Long> alunos // IDs dos alunos
) {}