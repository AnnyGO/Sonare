package sonare.api_tcc.DTO.Curso;

import sonare.api_tcc.DTO.Modulo.RegistroModuloDTO;

import java.util.List;

public record RegistroCursoDTO(
        String nome,
        String descricao,
        List<RegistroModuloDTO> modulos
) {
}
