package sonare.api_tcc.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonare.api_tcc.DTO.DiaDeAula.RegistroDiaDeAulaDTO;
import sonare.api_tcc.DTO.Turma.RegistroTurmaDTO;
import sonare.api_tcc.DTO.Turma.TurmaDTO;
import sonare.api_tcc.Entity.AlunoEntity;
import sonare.api_tcc.Entity.DiaDeAulaEntity;
import sonare.api_tcc.Entity.TurmaEntity;
import sonare.api_tcc.Enum.AlunoStatus;
import sonare.api_tcc.Exception.IdNaoEncontradoException;
import sonare.api_tcc.Exception.TurmaNaoPodeSerExcluidaException;
import sonare.api_tcc.Repository.AlunoRepository;
import sonare.api_tcc.Repository.TurmaRepository;

import java.util.List;
@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private DiaDeAulaService diaDeAulaService;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ModuloService moduloService;

    @Transactional
    public TurmaDTO create (RegistroTurmaDTO data){
        TurmaEntity turma = turmaRepository.save(
                new TurmaEntity(data.nome(),
                        cursoService.getEntityById(data.cursoId()),
                        moduloService.getEntityId(data.moduloId())));
        return toDTO(turma);
    }

    public TurmaEntity getEntityById (Long turmaId){
        return turmaRepository.findById(turmaId).orElseThrow(()
                -> new IdNaoEncontradoException("Turma não encontrada com o Id: " + turmaId));
    }

    public TurmaDTO getById (Long turmaId){
        TurmaEntity turma = getEntityById(turmaId);
        return toDTO(turma);
    }

    public List<TurmaDTO> getAll (){
        List<TurmaEntity> turmas = turmaRepository.findAll();
        return turmas.stream().map(this::toDTO).toList();
    }

    @Transactional
    public TurmaDTO update (Long turmaId, RegistroTurmaDTO data){
        TurmaEntity turma = this.getEntityById(turmaId);
        turma.setNome(data.nome());
        turmaRepository.save(turma);
        return toDTO(turma);
    }

    @Transactional
    public void delete (Long turmaId){
        TurmaEntity turma = this.getEntityById(turmaId);
        if (!turma.getAlunos().isEmpty()){
            throw new TurmaNaoPodeSerExcluidaException("Não é possível excluir uma turma que ainda possui alunos vinculados");
        }
        turmaRepository.delete(turma);
    }

    public TurmaEntity adicionarDiaDeAula (RegistroDiaDeAulaDTO diaDeAulaDTO, Long turmaId){
       TurmaEntity turma = this.getEntityById(turmaId);
           DiaDeAulaEntity diaDeAula = diaDeAulaService.create(diaDeAulaDTO,turma);
           turma.getDiasDeAula().add(diaDeAula);

       return turmaRepository.save(turma);
    }
    public TurmaEntity removerDiaDeAula (Long diaDeAulaId, Long turmaId){
        TurmaEntity turma = this.getEntityById(turmaId);
        DiaDeAulaEntity diaDeAula = this.diaDeAulaService.getEntityById(diaDeAulaId);
        turma.getDiasDeAula().remove(diaDeAula);
        diaDeAulaService.delete(diaDeAulaId);
        return turmaRepository.save(turma);
    }

    public TurmaEntity adicionarAluno (List<Long> alunoId, Long turmaId){
        TurmaEntity turma = this.getEntityById(turmaId);
        alunoId.forEach(id -> {
            AlunoEntity aluno = this.alunoService.getEntityById(id);
            aluno.setTurma(turma);
            turma.getAlunos().add(aluno);
            aluno.setStatus(AlunoStatus.CURSANDO);
            alunoRepository.save(aluno);
        });
        return turmaRepository.save(turma);
    }

    public TurmaEntity removerAluno (Long alunoId, Long turmaId) {
        TurmaEntity turma = this.getEntityById(turmaId);
        AlunoEntity aluno = this.alunoService.getEntityById(alunoId);
        turma.getAlunos().remove(aluno);
        aluno.setStatus(AlunoStatus.ATIVO);
        aluno.setTurma(null);
        alunoRepository.save(aluno);
        return turmaRepository.save(turma);
    }

    private TurmaDTO toDTO (TurmaEntity turma){
        return new TurmaDTO(turma.getTurmaId(), turma.getNome(),turma.getModulo(), turma.getCurso(), turma.getDataCriacao(), turma.getDiasDeAula(), turma.getAlunos());
    }
}
