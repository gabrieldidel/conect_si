package conectSI.conect_si.services;

import conectSI.conect_si.model.Postagem;
import conectSI.conect_si.model.Usuario;
import conectSI.conect_si.model.dto.PostagemDTO;
import conectSI.conect_si.repository.PostagemRepository;
import conectSI.conect_si.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostagemService {

    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;

    // Lista todas as postagens
    public List<Postagem> listarPostagens() {
        return postagemRepository.findAll();
    }

    // Busca uma postagem por ID
    public Optional<Postagem> buscarPorId(Integer id) {
        return postagemRepository.findById(id);
    }

    // Cria uma nova postagem
    public Postagem criarPostagem(String authorization, PostagemDTO postagemDTO) {
        Long userId = JwtService.extractId(authorization);

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID do token."));

        Postagem postagem = new Postagem();
        postagem.setTitulo(postagemDTO.getTitulo());
        postagem.setConteudo(postagemDTO.getConteudo());
        postagem.setUsuario(usuario);

        return postagemRepository.save(postagem);
    }

    // Atualiza uma postagem existente
    public Postagem atualizarPostagem(Integer id, PostagemDTO postagemDTO) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada."));

        postagem.setTitulo(postagemDTO.getTitulo());
        postagem.setConteudo(postagemDTO.getConteudo());

        return postagemRepository.save(postagem);
    }

    // Deleta uma postagem
    public void deletarPostagem(Integer id) {
        postagemRepository.deleteById(id);
    }
}
