package sonare.api_tcc.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import sonare.api_tcc.Enum.DiaDaSemana;

import java.time.LocalTime;

@Entity
@Table(name = "diasDeAula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "diaDeAulaId")
public class DiaDeAulaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaDeAulaId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horarioInicio;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horarioFim;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    @JsonBackReference
    private TurmaEntity turma;

    public DiaDeAulaEntity(DiaDaSemana diaDaSemana, String horarioInicio, String horarioFim, TurmaEntity turma) {
        this.diaDaSemana = diaDaSemana;
        this.horarioInicio = LocalTime.parse(horarioInicio);
        this.horarioFim = LocalTime.parse(horarioFim);
        this.turma = turma;
    }
}
