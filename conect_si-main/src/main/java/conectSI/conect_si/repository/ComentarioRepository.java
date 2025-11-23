package conectSI.conect_si.repository;

import conectSI.conect_si.model.Comentario;
import conectSI.conect_si.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    List<Comentario> findByPostagem(Postagem postagem);

    List<Comentario> findByPostagemId(Integer postagemId);
}