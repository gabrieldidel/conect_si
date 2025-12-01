package conectSI.conect_si.repository;

import conectSI.conect_si.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {
    List<Postagem> findByUsuarioId(Integer usuarioId);
    List<Postagem> findByUsuario_Id(Integer usuarioId);
}
