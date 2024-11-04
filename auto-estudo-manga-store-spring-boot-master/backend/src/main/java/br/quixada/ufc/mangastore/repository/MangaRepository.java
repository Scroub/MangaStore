package br.quixada.ufc.mangastore.repository;

import br.quixada.ufc.mangastore.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")
public interface MangaRepository extends JpaRepository<Manga, Long> {

}
