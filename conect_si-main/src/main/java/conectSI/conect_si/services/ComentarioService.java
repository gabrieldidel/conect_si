package conectSI.conect_si.services;

import conectSI.conect_si.model.Comentario;
import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.repository.ComentarioRepository;
import conectSI.conect_si.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    public Comentario salvarComentario(Integer postagemId, Comentario comentario) {
        Optional<Postagem> postagemOpt = postagemRepository.findById(postagemId);
        if (postagemOpt.isEmpty()) {
            throw new RuntimeException("Postagem não encontrada com ID: " + postagemId);
        }

        comentario.setPostagem(postagemOpt.get());
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> listarPorPostagem(Integer postagemId) {
        return comentarioRepository.findByPostagemId(postagemId);
    }
}