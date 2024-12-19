package sonare.api_tcc.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonare.api_tcc.DTO.DiaDeAula.DiaDeAulaDTO;
import sonare.api_tcc.DTO.DiaDeAula.RegistroDiaDeAulaDTO;
import sonare.api_tcc.Entity.DiaDeAulaEntity;
import sonare.api_tcc.Entity.TurmaEntity;
import sonare.api_tcc.Exception.ConflitoDiaDeAulaException;
import sonare.api_tcc.Exception.IdNaoEncontradoException;
import sonare.api_tcc.Repository.DiaDeAulaRepository;

import java.time.LocalTime;
import java.util.List;

@Service
public class DiaDeAulaService {
    @Autowired
    private DiaDeAulaRepository diaDeAulaRepository;

    @Transactional
    public DiaDeAulaEntity create (RegistroDiaDeAulaDTO data, TurmaEntity turma){
        DiaDeAulaEntity diaDeAula = new DiaDeAulaEntity(data.diaDaSemana(), data.horarioInicio(), data.horarioFim(), turma);
        validatarDiaDeAulaUnico(diaDeAula,turma);
        return diaDeAulaRepository.save(diaDeAula);
    }
    public DiaDeAulaEntity getEntityById (Long diaDeAulaId){
        return diaDeAulaRepository.findById(diaDeAulaId).orElseThrow(()
                -> new IdNaoEncontradoException("Dia de Aula não encontrado com o Id: " + diaDeAulaId));
    }

    public DiaDeAulaDTO getById(Long diaDeAulaId){
        DiaDeAulaEntity diaDeAula = getEntityById(diaDeAulaId);
        return toDTO(diaDeAula);
    }

    public List<DiaDeAulaDTO> getAll (){
        List<DiaDeAulaEntity> diasDeAula = diaDeAulaRepository.findAll();
        return diasDeAula.stream().map(this::toDTO).toList();
    }

    @Transactional
    public DiaDeAulaDTO update (Long diaDeAulaId, RegistroDiaDeAulaDTO data){
        DiaDeAulaEntity diaDeAula = getEntityById(diaDeAulaId);
        diaDeAula.setDiaDaSemana(data.diaDaSemana());
        diaDeAula.setHorarioInicio(LocalTime.parse(data.horarioInicio()));
        diaDeAula.setHorarioFim(LocalTime.parse(data.horarioFim()));

        validatarDiaDeAulaUnico(diaDeAula, diaDeAula.getTurma());
        diaDeAulaRepository.save(diaDeAula);

        return toDTO(diaDeAula);
    }
    @Transactional
    public void delete (Long diaDeAulaId){
        DiaDeAulaEntity diaDeAula = getEntityById(diaDeAulaId);
        diaDeAulaRepository.delete(diaDeAula);
    }

    public void validatarDiaDeAulaUnico(DiaDeAulaEntity diaDeAula, TurmaEntity turma) {
        List<DiaDeAulaEntity> conflitoDiaDeAula = diaDeAulaRepository.
                findByDiaDaSemanaAndHorarioInicioAndHorarioFimAndTurma(
                        diaDeAula.getDiaDaSemana(),
                        diaDeAula.getHorarioInicio(),
                        diaDeAula.getHorarioFim(),
                        turma);
        if (!conflitoDiaDeAula.isEmpty()) {
            throw new ConflitoDiaDeAulaException("Já existe uma turma com o mesmo dia da semana e horários.");
        }
    }

    private DiaDeAulaDTO toDTO (DiaDeAulaEntity diaDeAula){
        return new DiaDeAulaDTO(diaDeAula.getDiaDeAulaId(),
                diaDeAula.getDiaDaSemana(),
                diaDeAula.getHorarioInicio().toString(),
                diaDeAula.getHorarioFim().toString());
    }
}
