package br.quixada.ufc.mangastore.repository;

import br.quixada.ufc.mangastore.model.Usuario;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;

@CrossOrigin("http://localhost:3000")
public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

}
