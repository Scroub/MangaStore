package br.quixada.ufc.mangastore.config;

import br.quixada.ufc.mangastore.projection.CustomManga;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import br.quixada.ufc.mangastore.model.Manga;
import br.quixada.ufc.mangastore.model.Comentario;

@Configuration
@Component
public class RestConfig  implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Manga.class,Comentario.class);

        //adicionando projection
        config.getProjectionConfiguration()
                .addProjection(CustomManga.class);

    }

}
