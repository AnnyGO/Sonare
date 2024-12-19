package sonare.api_tcc.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonare.api_tcc.DTO.Curso.CursoDTO;
import sonare.api_tcc.DTO.Curso.RegistroCursoDTO;
import sonare.api_tcc.Entity.CursoEntity;
import sonare.api_tcc.Exception.IdNaoEncontradoException;
import sonare.api_tcc.Repository.CursoRepository;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public CursoDTO create (RegistroCursoDTO data){
        CursoEntity curso = cursoRepository.save(new CursoEntity(data.nome(), data.descricao()));
        return toDTO(curso);
    }

    public CursoEntity getEntityById (Long cursoId){
        return cursoRepository.findById(cursoId).orElseThrow(()
                -> new IdNaoEncontradoException("Curso não encontrado com o Id: " + cursoId));
    }

    public CursoDTO getById (Long cursoId){
        CursoEntity curso = getEntityById(cursoId);
        return toDTO(curso);
    }

    public List<CursoDTO> getAll (){
        List<CursoEntity> cursos = cursoRepository.findAll();
        return cursos.stream().map(this::toDTO).toList();
    }

    @Transactional
    public CursoDTO update (Long cursoId, RegistroCursoDTO data){
        CursoEntity curso = this.getEntityById(cursoId);
        curso.setNome(data.nome());
        curso.setDescricao(data.descricao());

        cursoRepository.save(curso);

        return toDTO(curso);
    }

    @Transactional
    public void delete (Long cursoId){
        CursoEntity curso = this.getEntityById(cursoId);
        cursoRepository.delete(curso);
    }

    private CursoDTO toDTO (CursoEntity curso){
        return new CursoDTO(curso.getCursoId(), curso.getNome(), curso.getDescricao(), curso.getDataCriacao(), curso.getModulos());
    }
}
