package sonare.api_tcc.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "modulos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "moduloId")
public class ModuloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduloId;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column (nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "modulo")
    @JsonIgnore
    private List<TurmaEntity> turmas;

    @ManyToOne
    @JoinColumn(name = "cursoId", nullable = false)
    @JsonBackReference
    private CursoEntity curso;

    public ModuloEntity(String nome, String descricao, CursoEntity curso) {
        this.nome = nome;
        this.descricao = descricao;
        this.curso = curso;
    }

}
