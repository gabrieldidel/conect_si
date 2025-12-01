package conectSI.conect_si.model.dto;

import conectSI.conect_si.model.Postagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostagemDTO {
    private String titulo;
    private String conteudo;
    private Integer usuario;

    public PostagemDTO (String titulo, String conteudo, Integer usuario) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;

    }


}
