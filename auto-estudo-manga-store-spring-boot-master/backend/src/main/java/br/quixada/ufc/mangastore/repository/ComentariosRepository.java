package br.quixada.ufc.mangastore.repository;

import br.quixada.ufc.mangastore.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")
public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

}
