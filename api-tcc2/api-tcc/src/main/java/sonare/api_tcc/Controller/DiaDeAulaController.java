package sonare.api_tcc.Controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonare.api_tcc.DTO.DiaDeAula.DiaDeAulaDTO;
import sonare.api_tcc.DTO.DiaDeAula.RegistroDiaDeAulaDTO;
import sonare.api_tcc.Entity.DiaDeAulaEntity;
import sonare.api_tcc.Entity.TurmaEntity;
import sonare.api_tcc.Repository.DiaDeAulaRepository;
import sonare.api_tcc.Service.DiaDeAulaService;
import sonare.api_tcc.Service.TurmaService;

import java.util.List;

@RestController
@RequestMapping("diasDeAula")
public class DiaDeAulaController {
    @Autowired
    private DiaDeAulaService diaDeAulaService;

    @Autowired
    private DiaDeAulaRepository diaDeAulaRepository;

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<DiaDeAulaDTO>> getAll (){
        return ResponseEntity.status(HttpStatus.OK).body(this.diaDeAulaService.getAll());
    }
    @GetMapping("/{diaDeAulaId}")
    public ResponseEntity<DiaDeAulaDTO> getById (@PathVariable Long diaDeAulaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.diaDeAulaService.getById(diaDeAulaId));
    }
    @GetMapping("/turmas/{turmaId}")
    public ResponseEntity<List<DiaDeAulaEntity>> getDiasDeAulaByTurma (@PathVariable Long turmaId){
        return ResponseEntity.status(HttpStatus.OK).body(this.diaDeAulaRepository.findByTurma(this.turmaService.getEntityById(turmaId)));
    }

    @PutMapping("/{diaDeAulaId}")
    public ResponseEntity<DiaDeAulaDTO> update (@PathVariable Long diaDeAulaId, @RequestBody RegistroDiaDeAulaDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(this.diaDeAulaService.update(diaDeAulaId,data));
    }

    @DeleteMapping("/{diaDeAulaId}")
    public ResponseEntity<String> delete (@PathVariable Long diaDeAulaId){
        diaDeAulaService.delete(diaDeAulaId);
        return ResponseEntity.status(HttpStatus.OK).body("Dia de Aula deletado com sucesso!");
    }
}
