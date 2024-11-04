package br.quixada.ufc.mangastore.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private int rating;
    @ManyToOne
    private Manga manga;
}
