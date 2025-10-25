package conectSI.conect_si.controller;

import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.model.dto.PostagemDTO;
import conectSI.conect_si.model.dto.PostagemResponseDTO;
import conectSI.conect_si.services.PostagemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // ou restrinja para o domínio do seu frontend
@RestController
@RequestMapping("/postagens")
@AllArgsConstructor
public class PostagemController {

    private final PostagemService postagemService;

    // GET - Lista todas as postagens
    @GetMapping
    public List<PostagemResponseDTO> listarPostagens() {
        return postagemService.listarPostagens()
                .stream()
                .map(PostagemResponseDTO::from)
                .toList();
    }

    // GET - Busca postagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<PostagemResponseDTO> buscarPostagem(@PathVariable Integer id) {
        return postagemService.buscarPorId(id)
                .map(PostagemResponseDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Cria nova postagem
    @PostMapping
    public ResponseEntity<PostagemResponseDTO> criarPostagem(
            @RequestHeader String authorization,
            @Valid @RequestBody PostagemDTO postagemDTO
    ) {
        Postagem nova = postagemService.criarPostagem(authorization, postagemDTO);
        return ResponseEntity.status(201).body(PostagemResponseDTO.from(nova));
    }

    // PUT - Atualiza postagem existente
    @PutMapping("/{id}")
    public ResponseEntity<PostagemResponseDTO> atualizarPostagem(
            @PathVariable Integer id,
            @Valid @RequestBody PostagemDTO postagemDTO
    ) {
        Postagem atualizada = postagemService.atualizarPostagem(id, postagemDTO);
        return ResponseEntity.ok(PostagemResponseDTO.from(atualizada));
    }

    // DELETE - Remove postagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable Integer id) {
        return postagemService.buscarPorId(id)
                .map(p -> {
                    postagemService.deletarPostagem(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
