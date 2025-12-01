package conectSI.conect_si.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TokenDTO {
    private String token;

    private String nome;
    private String email;

    private List<PostagemDTO> postagens;
}
