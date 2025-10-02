package conectSI.conect_si.services;

import conectSI.conect_si.Core.SecurityConfig;
import conectSI.conect_si.model.Usuario;
import conectSI.conect_si.model.dto.LoginDTO;
import conectSI.conect_si.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SecurityConfig securityConfig;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setSenha(securityConfig.passwordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Boolean buscarUsuario (LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .map(usuario -> passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha()))
                .orElse(false);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


}
