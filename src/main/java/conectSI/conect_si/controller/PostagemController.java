package conectSI.conect_si.controller;

import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.services.PostagemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postagens")
@AllArgsConstructor
public class PostagemController {

    private final PostagemService postagemService;

    // Listar postagens com paginação
    @GetMapping
    public Page<Postagem> listarPostagens(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("dataCriacao").descending());
        return postagemService.listarPostagens(pageable);
    }

    // Buscar postagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPostagem(@PathVariable Integer id) {
        return postagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar nova postagem
    @PostMapping
    public Postagem criarPostagem(@RequestBody Postagem postagem) {
        return postagemService.salvarPostagem(postagem);
    }

    // Atualizar postagem existente
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

    // Deletar postagem
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
