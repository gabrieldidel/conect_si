package conectSI.conect_si.services;

import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.repository.PostagemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostagemService {

    private final PostagemRepository postagemRepository;

    // Lista todas as postagens (sem paginação)
    public List<Postagem> listarPostagens() {
        return postagemRepository.findAll();
    }

    // Lista postagens com paginação
    public Page<Postagem> listarPostagens(Pageable pageable) {
        return postagemRepository.findAll(pageable);
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
