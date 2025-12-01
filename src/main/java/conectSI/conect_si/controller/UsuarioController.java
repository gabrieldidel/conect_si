package conectSI.conect_si.controller;

import conectSI.conect_si.model.Usuario;
import conectSI.conect_si.model.dto.TokenDTO;
import conectSI.conect_si.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/user-logado")
    public ResponseEntity<TokenDTO> getUserLogado(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        TokenDTO dto = usuarioService.retornaInfoDoToken(token);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.buscarPorId(id)
                .map(u -> {
                    u.setNome(usuario.getNome());
                    u.setEmail(usuario.getEmail());
                    usuarioService.salvarUsuario(u);
                    return ResponseEntity.ok(u);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(u -> {
                    usuarioService.deletarUsuario(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
