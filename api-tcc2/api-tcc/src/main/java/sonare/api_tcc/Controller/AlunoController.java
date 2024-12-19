package sonare.api_tcc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonare.api_tcc.DTO.Aluno.AlunoDTO;
import sonare.api_tcc.DTO.Aluno.RegistroAlunoDTO;
import sonare.api_tcc.Entity.AlunoEntity;
import sonare.api_tcc.Enum.AlunoStatus;
import sonare.api_tcc.Repository.AlunoRepository;
import sonare.api_tcc.Service.AlunoService;
import sonare.api_tcc.Service.TurmaService;

import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<AlunoDTO> create (@RequestBody RegistroAlunoDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.alunoService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.getAll());
    }

    @GetMapping("/relatorioStatus")
    public ResponseEntity<List<AlunoEntity>> relatorioStatus (@RequestParam AlunoStatus status){
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findByStatus(status.toString()));
    }

    @GetMapping("/turmas/{turmaId}")
    public ResponseEntity<List<AlunoEntity>> relatorioAlunos (@PathVariable Long turmaId ){
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findByTurma(turmaService.getEntityById(turmaId)));
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<AlunoDTO> getById(@PathVariable Long alunoId){
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.getById(alunoId));
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long alunoId, @RequestBody RegistroAlunoDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(this.alunoService.update(alunoId,data));
    }
    @DeleteMapping("/{alunoId}")
    public ResponseEntity<String> delete (@PathVariable Long alunoId){
        this.alunoService.delete(alunoId);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

}
