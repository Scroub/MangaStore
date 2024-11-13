package br.quixada.ufc.mangastore.repository;

import br.quixada.ufc.mangastore.model.Manga;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")
public interface MangaRepository extends JpaRepository<Manga, Long> {
    @Query("SELECT m FROM Manga m LEFT JOIN FETCH m.comments WHERE m.gender = :gender")
    List<Manga> findMangasByCategory(@Param("gender") String gender);
}
