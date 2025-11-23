package conectSI.conect_si.controller;

import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.model.dto.PostagemDTO;
import conectSI.conect_si.services.PostagemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
@AllArgsConstructor
public class PostagemController {

    private final PostagemService postagemService;

    @GetMapping
    public List<Postagem> listarPostagens() {
        return postagemService.listarPostagens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPostagem(@PathVariable Integer id) {
        return postagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Postagem criarPostagem(
            @RequestHeader String authorization,
            @RequestBody PostagemDTO postagem
    ) {
        return postagemService.salvarPostagem(authorization, postagem);
    }

    @PutMapping("/{id}")
    public Postagem atualizarPostagem(
            @RequestHeader String authorization,
            @PathVariable Integer id,
            @RequestBody PostagemDTO postagem) {
        return postagemService.salvarPostagem(authorization, postagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable Integer id) {
        return postagemService.buscarPorId(id)
                .map(p -> {
                    postagemService.deletarPostagem(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
