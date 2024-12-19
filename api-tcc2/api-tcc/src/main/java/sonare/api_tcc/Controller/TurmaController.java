package sonare.api_tcc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonare.api_tcc.DTO.DiaDeAula.RegistroDiaDeAulaDTO;
import sonare.api_tcc.DTO.Turma.RegistroTurmaDTO;
import sonare.api_tcc.DTO.Turma.TurmaDTO;
import sonare.api_tcc.Entity.TurmaEntity;
import sonare.api_tcc.Repository.TurmaRepository;
import sonare.api_tcc.Service.TurmaService;
import java.util.List;

@RestController
@RequestMapping("turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    public ResponseEntity<TurmaDTO> create (@RequestBody RegistroTurmaDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.turmaService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> getAll (){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.getAll());
    }

    @GetMapping("/relatorioFindByCurso")
    public ResponseEntity<List<TurmaEntity>> relatorioFindByCurso(@RequestParam Long cursoId) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.findByCursoId(cursoId));
    }

    @GetMapping("/{turmaId}")
    public ResponseEntity<TurmaDTO> getById (@PathVariable Long turmaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.getById(turmaId));
    }

    @PutMapping("/{turmaId}")
    public ResponseEntity<TurmaDTO> update (@PathVariable Long turmaId, @RequestBody RegistroTurmaDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.update(turmaId, data));
    }

    @PutMapping("/{turmaId}/diasDeAula")
    public ResponseEntity<TurmaEntity> adicionarDiaDeAula (@RequestBody RegistroDiaDeAulaDTO diaDeAulaDTO, @PathVariable Long turmaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.adicionarDiaDeAula(diaDeAulaDTO, turmaId));
    }

    @PutMapping("/{turmaId}/alunos")
    public ResponseEntity<TurmaEntity> adicionarAluno (@RequestBody List<Long> alunoId, @PathVariable Long turmaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.adicionarAluno(alunoId,turmaId));
    }

    @DeleteMapping("/{turmaId}/diasDeAula/{diaDeAulaId}")
    public ResponseEntity<TurmaEntity> removerDiaDeAula (@PathVariable Long diaDeAulaId, @PathVariable Long turmaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.removerDiaDeAula(diaDeAulaId, turmaId)) ;
    }

    @DeleteMapping("/{turmaId}/alunos/{alunoId}")
    public ResponseEntity<TurmaEntity> removerAluno (@PathVariable Long alunoId, @PathVariable Long turmaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.turmaService.removerAluno(alunoId, turmaId));
    }

    @DeleteMapping("/{turmaId}")
    public ResponseEntity<String> delete (@PathVariable Long turmaId){
        turmaService.delete(turmaId);
        return ResponseEntity.status(HttpStatus.OK).body("Turma deletada com sucesso!");
    }
}
