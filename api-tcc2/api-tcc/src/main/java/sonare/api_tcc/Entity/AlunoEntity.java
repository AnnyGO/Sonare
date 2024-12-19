package sonare.api_tcc.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sonare.api_tcc.Enum.AlunoStatus;

import java.time.LocalDate;

@Entity
@Table (name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "alunoId")
public class AlunoEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long alunoId;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false, unique = true)
    private String CPF;

    @Column (nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column (nullable = false)
    private LocalDate dataNascimento;

    //Trabalhar com ID do curso (FK)

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "turmaId")
    private TurmaEntity turma;
    
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private AlunoStatus status = AlunoStatus.ATIVO;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column (nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    public AlunoEntity(String nome, String CPF, String telefone, LocalDate dataNascimento, String logradouro, String numero) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.logradouro = logradouro;
        this.numero = numero;
        this.status = AlunoStatus.ATIVO;
        this.dataCriacao = LocalDate.now();
    }
}
