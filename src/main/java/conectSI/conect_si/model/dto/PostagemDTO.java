package conectSI.conect_si.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostagemDTO {
    @NotBlank(message = "O título não pode estar vazio.")
    private String titulo;

    @NotBlank(message = "O conteúdo não pode estar vazio.")
    private String conteudo;
}

