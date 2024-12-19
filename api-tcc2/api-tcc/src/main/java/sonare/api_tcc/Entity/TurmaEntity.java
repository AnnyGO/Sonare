package sonare.api_tcc.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "turmaId")
public class TurmaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long turmaId;

    @Column(nullable = false, unique = true)
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataCriacao;

    //Inserir Lista de Alunos
    @OneToMany(mappedBy = "turma")
    private List<AlunoEntity> alunos = new ArrayList<>();

    //Inserir Dias de Aula
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<DiaDeAulaEntity> diasDeAula = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "moduloId")
    private ModuloEntity modulo;

    //Inserir atributo do curso
    @ManyToOne
    @JoinColumn(name = "cursoId", nullable = false)
    private CursoEntity curso;
    public TurmaEntity(String nome, CursoEntity curso, ModuloEntity modulo) {
        this.nome = nome;
        this.dataCriacao = LocalDate.now();
        this.curso = curso;
        this.modulo = modulo;
    }
}
