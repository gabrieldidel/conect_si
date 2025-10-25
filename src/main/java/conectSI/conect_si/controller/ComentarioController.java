package conectSI.conect_si.controller;

import conectSI.conect_si.model.Comentario;
import conectSI.conect_si.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/{postagemId}")
    public Comentario criarComentario(
            @PathVariable Integer postagemId,
            @RequestBody Comentario comentario
    ) {
        return comentarioService.salvarComentario(postagemId, comentario);
    }

    @GetMapping("/{postagemId}")
    public List<Comentario> listarPorPostagem(@PathVariable Integer postagemId) {
        return comentarioService.listarPorPostagem(postagemId);
    }
}