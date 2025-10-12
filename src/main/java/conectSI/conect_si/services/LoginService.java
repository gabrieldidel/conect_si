package conectSI.conect_si.services;

import conectSI.conect_si.model.Usuario;
import conectSI.conect_si.model.dto.LoginDTO;
import conectSI.conect_si.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public String login(LoginDTO loginDTO){
        return usuarioService.buscarUsuario(loginDTO);
    }

}
