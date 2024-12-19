package sonare.api_tcc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonare.api_tcc.DTO.Modulo.ModuloDTO;
import sonare.api_tcc.DTO.Modulo.RegistroModuloDTO;
import sonare.api_tcc.Entity.ModuloEntity;
import sonare.api_tcc.Service.CursoService;
import sonare.api_tcc.Service.ModuloService;

import java.util.List;

@RestController
@RequestMapping("modulos")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<ModuloEntity> create (@RequestBody RegistroModuloDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.moduloService.create(data, cursoService.getEntityById(data.cursoId()) ));
    }
    @GetMapping
    public ResponseEntity<List<ModuloDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.getAll());
    }
    @GetMapping("/{moduloId}")
    public ResponseEntity<ModuloDTO> getById(@PathVariable Long moduloId){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.getById(moduloId));
    }
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ModuloDTO>> getByCursoId (@PathVariable Long cursoId){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.getByCursoId(cursoId));
    }
    @PutMapping("/{moduloId}")
    public ResponseEntity<ModuloDTO> update (@PathVariable Long moduloId, @RequestBody RegistroModuloDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduloService.update(moduloId,data));
    }
    @DeleteMapping("/{moduloId}")
    public ResponseEntity<String> delete (@PathVariable Long moduloId){
        this.moduloService.delete(moduloId);
        return ResponseEntity.status(HttpStatus.OK).body("Módulo deletado com sucesso!");
    }
}
