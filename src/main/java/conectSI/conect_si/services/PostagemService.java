package conectSI.conect_si.services;

import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.repository.PostagemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostagemService {

    private final PostagemRepository postagemRepository;

    public List<Postagem> listarPostagens() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> buscarPorId(Integer id) {
        return postagemRepository.findById(id);
    }

    public Postagem salvarPostagem(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    public void deletarPostagem(Integer id) {
        postagemRepository.deleteById(id);
    }
}
