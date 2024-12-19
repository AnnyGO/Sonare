package sonare.api_tcc.DTO.DiaDeAula;

import sonare.api_tcc.Enum.DiaDaSemana;

public record DiaDeAulaDTO(
        Long diaDeAulaId,
        DiaDaSemana diaDaSemana,
        String horarioInicio,
        String horarioFim
) {
}
