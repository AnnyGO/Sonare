package sonare.api_tcc.DTO.Aluno;

import com.fasterxml.jackson.annotation.JsonFormat;
import sonare.api_tcc.Enum.AlunoStatus;

import java.time.LocalDate;

public record RegistroAlunoDTO(
        String nome,
        String CPF,
        String telefone,
        String logradouro,
        String numero,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        AlunoStatus status
) {
}
