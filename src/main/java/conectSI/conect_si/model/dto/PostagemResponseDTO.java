package conectSI.conect_si.model.dto;

import conectSI.conect_si.model.Postagem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostagemResponseDTO {
    private Integer id;
    private String titulo;
    private String conteudo;
    private String usuario;
    private LocalDateTime dataCriacao;

    public static PostagemResponseDTO from(Postagem p) {
        return new PostagemResponseDTO(
                p.getId(),
                p.getTitulo(),
                p.getConteudo(),
                p.getUsuario().getNome(),
                p.getDataCriacao()
        );
    }
}
