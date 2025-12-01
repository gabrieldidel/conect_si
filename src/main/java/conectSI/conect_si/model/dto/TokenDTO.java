package conectSI.conect_si.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {
    private String token;

    private String nome;
    private String email;
}
