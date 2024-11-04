package br.quixada.ufc.mangastore.projection;

import br.quixada.ufc.mangastore.model.Comentario;
import br.quixada.ufc.mangastore.model.Manga;
import org.springframework.data.rest.core.config.Projection;


import java.util.List;

@Projection(name = "customManga", types = {Manga.class})
public interface CustomManga {
    Long getId();
    String getTitle();
    Long getNumber();
    float getPrice();
    String getCover();
    String getSummary();
    List<Comentario> getComments();
}
