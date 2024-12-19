package sonare.api_tcc.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonare.api_tcc.DTO.Modulo.ModuloDTO;
import sonare.api_tcc.DTO.Modulo.RegistroModuloDTO;
import sonare.api_tcc.Entity.CursoEntity;
import sonare.api_tcc.Entity.ModuloEntity;
import sonare.api_tcc.Exception.IdNaoEncontradoException;
import sonare.api_tcc.Repository.ModuloRepository;

import java.util.List;

@Service
public class ModuloService {
    @Autowired
    private ModuloRepository moduloRepository;

    @Transactional
    public ModuloEntity create (RegistroModuloDTO data, CursoEntity curso){
        ModuloEntity modulo = new ModuloEntity(data.nome(), data.descricao(), curso);
        return moduloRepository.save(modulo);
    }

    public ModuloEntity getEntityId (Long moduloId){
        return moduloRepository.findById(moduloId).orElseThrow(()
                -> new IdNaoEncontradoException("Módulo não encontrado com o Id: " + moduloId));
    }

    public ModuloDTO getById (Long moduloId){
        ModuloEntity modulo = getEntityId(moduloId);
        return toDTO(modulo);
    }
    public List<ModuloDTO> getByCursoId(Long cursoId) {
        List<ModuloEntity> modulos = moduloRepository.findByCursoCursoId(cursoId);
        return modulos.stream().map(this::toDTO).toList();
    }

    public List<ModuloDTO> getAll (){
        List<ModuloEntity> modulos = moduloRepository.findAll();
        return modulos.stream().map(this::toDTO).toList();
    }

    @Transactional
    public ModuloDTO update (Long moduloId, RegistroModuloDTO data){
        ModuloEntity modulo = getEntityId(moduloId);
        modulo.setNome(data.nome());
        modulo.setDescricao(data.descricao());
        moduloRepository.save(modulo);

        return toDTO(modulo);
    }

    @Transactional
    public void delete (Long moduloId){
        ModuloEntity modulo = getEntityId(moduloId);
        moduloRepository.delete(modulo);
    }

    private ModuloDTO toDTO (ModuloEntity modulo){
        return new ModuloDTO(modulo.getModuloId(),
                modulo.getNome(),
                modulo.getDescricao());
    }
}
