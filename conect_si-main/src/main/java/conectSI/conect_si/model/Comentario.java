package conectSI.conect_si.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {

    @Id
    @Column(nullable = false)
    private Integer id; // id definido manualmente

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "postagem_id", nullable = false)
    private Postagem postagem;
}