package sonare.api_tcc.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sonare.api_tcc.DTO.Aluno.AlunoDTO;
import sonare.api_tcc.DTO.Aluno.RegistroAlunoDTO;
import sonare.api_tcc.Entity.AlunoEntity;
import sonare.api_tcc.Exception.CpfException;
import sonare.api_tcc.Exception.IdNaoEncontradoException;
import sonare.api_tcc.Repository.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public AlunoDTO create (RegistroAlunoDTO data){
        verificarCPF(data.CPF());

        AlunoEntity aluno = new AlunoEntity(
                data.nome(),
                data.CPF(),
                data.telefone(),
                data.dataNascimento(),
                data.logradouro(),
                data.numero()
        );
        alunoRepository.save(aluno);

        return toDTO(aluno);
    }

    public AlunoEntity getEntityById (Long alunoId){
        return alunoRepository.findById(alunoId).orElseThrow(()
                -> new IdNaoEncontradoException("Aluno não encontrado com o Id: " + alunoId));
    }

    public AlunoDTO getById(Long alunoId){
        AlunoEntity aluno = getEntityById(alunoId);
        return toDTO(aluno);
    }

    public List<AlunoDTO> getAll(){
        List<AlunoEntity> alunos = alunoRepository.findAll();
        return alunos.stream().map(this::toDTO).toList();
    }

    @Transactional
    public AlunoDTO update (Long alunoId, RegistroAlunoDTO data){
        AlunoEntity aluno = this.getEntityById(alunoId);
        aluno.setNome(data.nome());
        aluno.setTelefone(data.telefone());
        aluno.setDataNascimento(data.dataNascimento());
        aluno.setLogradouro(data.logradouro());
        aluno.setNumero(data.numero());
        aluno.setStatus(data.status());

        alunoRepository.save(aluno);

        return toDTO(aluno);
    }

    @Transactional
    public void delete (Long alunoId){
        AlunoEntity aluno = this.getEntityById(alunoId);
        this.alunoRepository.delete(aluno);
    }

    private void verificarCPF(String CPF){
        CPF = CPF.replaceAll("\\D", "");

        // Verifica se o CPF possui 11 dígitos
        if (CPF.length() != 11) {
            throw new CpfException();
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * Character.getNumericValue(CPF.charAt(i));
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * Character.getNumericValue(CPF.charAt(i));
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }

        // Verifica se os dígitos verificadores calculados correspondem aos do CPF

        if(!(Character.getNumericValue(CPF.charAt(9)) == firstDigit &&
                Character.getNumericValue(CPF.charAt(10)) == secondDigit)){
            throw new CpfException();
        }
    }

    private AlunoDTO toDTO (AlunoEntity aluno){

        return new AlunoDTO(aluno.getAlunoId(),
                aluno.getNome(),
                aluno.getCPF(),
                aluno.getTelefone(),
                aluno.getLogradouro(),
                aluno.getNumero(),
                aluno.getDataNascimento(),
                aluno.getDataCriacao(),
                aluno.getStatus());
    }
}
