package sonare.api_tcc.DTO.DiaDeAula;

import sonare.api_tcc.Enum.DiaDaSemana;

public record RegistroDiaDeAulaDTO(
        DiaDaSemana diaDaSemana,
        String horarioInicio,
        String horarioFim
) {
}
