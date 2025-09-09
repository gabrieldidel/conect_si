package conectSI.conect_si.controller;

import conectSI.conect_si.model.Postagem;
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
    public Postagem criarPostagem(@RequestBody Postagem postagem) {
        return postagemService.salvarPostagem(postagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizarPostagem(@PathVariable Integer id, @RequestBody Postagem postagem) {
        return postagemService.buscarPorId(id)
                .map(p -> {
                    p.setTitulo(postagem.getTitulo());
                    p.setConteudo(postagem.getConteudo());
                    return ResponseEntity.ok(postagemService.salvarPostagem(p));
                })
                .orElse(ResponseEntity.notFound().build());
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
