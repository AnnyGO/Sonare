package sonare.api_tcc.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cursoId")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cursoId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    //Inserir imagem de capa futuramente

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataCriacao;

    //Inserir Lista de Turmas
    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<TurmaEntity> turmas = new ArrayList<>();

    //Inserir Lista de Módulos
    @OneToMany(mappedBy = "curso")
    private List<ModuloEntity> modulos = new ArrayList<>();

    public CursoEntity(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
    }
}
