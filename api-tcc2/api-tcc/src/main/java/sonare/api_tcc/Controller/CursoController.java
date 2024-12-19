package sonare.api_tcc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonare.api_tcc.DTO.Curso.CursoDTO;
import sonare.api_tcc.DTO.Curso.RegistroCursoDTO;
import sonare.api_tcc.Entity.CursoEntity;
import sonare.api_tcc.Repository.CursoRepository;
import sonare.api_tcc.Service.CursoService;

import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<CursoDTO> create (@RequestBody RegistroCursoDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.getAll());
    }

    @GetMapping("/relatorioFindByQuantidadeMaximaTurmas")
    public ResponseEntity<List<CursoEntity>> relatorioFindByQuantidadeMaximaTurmas(@RequestParam int quantidadeMaxima) {
        return ResponseEntity.status(HttpStatus.OK).body(cursoRepository.findByQuantidadeMaximaTurmas(quantidadeMaxima));
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<CursoDTO> getById (@PathVariable Long cursoId){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.getById(cursoId));
    }

    @PutMapping("/{cursoId}")
    public ResponseEntity<CursoDTO> update (@PathVariable Long cursoId, @RequestBody RegistroCursoDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.update(cursoId,data));
    }

    @DeleteMapping("/{cursoId}")
    public ResponseEntity<String> delete (@PathVariable Long cursoId){
        this.cursoService.delete(cursoId);
        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso!");
    }
}
